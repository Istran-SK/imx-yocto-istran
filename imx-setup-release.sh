#!/bin/sh
#
# i.MX Yocto Project Build Environment Setup Script
#
# Copyright (C) 2011-2016 Freescale Semiconductor
# Copyright 2017 NXP
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 2 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

. sources/meta-imx/tools/setup-utils.sh

CWD=`pwd`
PROGNAME="setup-environment"
exit_message ()
{
   echo "To return to this build environment later please run:"
   echo "    source setup-environment <build_dir>"

}

usage()
{
    echo -e "\nUsage: source imx-setup-release.sh
    Optional parameters: [-b build-dir] [-h]"
echo "
    * [-b build-dir]: Build directory, if unspecified script uses 'build' as output directory
    * [-h]: help
"
}


clean_up()
{

    unset CWD BUILD_DIR FSLDISTRO
    unset fsl_setup_help fsl_setup_error fsl_setup_flag
    unset usage clean_up
    unset ARM_DIR META_FSL_BSP_RELEASE
    exit_message clean_up
}

# get command line options
OLD_OPTIND=$OPTIND
unset FSLDISTRO

while getopts "k:r:t:b:e:gh" fsl_setup_flag
do
    case $fsl_setup_flag in
        b) BUILD_DIR="$OPTARG";
           echo -e "\n Build directory is " $BUILD_DIR
           ;;
        h) fsl_setup_help='true';
           ;;
        \?) fsl_setup_error='true';
           ;;
    esac
done
shift $((OPTIND-1))
if [ $# -ne 0 ]; then
    fsl_setup_error=true
    echo -e "Invalid command line ending: '$@'"
fi
OPTIND=$OLD_OPTIND
if test $fsl_setup_help; then
    usage && clean_up && return 1
elif test $fsl_setup_error; then
    clean_up && return 1
fi


if [ -z "$DISTRO" ]; then
    if [ -z "$FSLDISTRO" ]; then
        FSLDISTRO='fsl-imx-xwayland'
    fi
else
    FSLDISTRO="$DISTRO"
fi

if [ -z "$BUILD_DIR" ]; then
    BUILD_DIR='build'
fi

if [ -z "$MACHINE" ]; then
    echo setting to default machine
    MACHINE='imx6qpsabresd'
fi

case $MACHINE in
imx8*)
    case $DISTRO in
    *wayland)
        : ok
        ;;
    *)
        echo -e "\n ERROR - Only Wayland distros are supported for i.MX 8 or i.MX 8M"
        echo -e "\n"
        return 1
        ;;
    esac
    ;;
*)
    : ok
    ;;
esac

# Cleanup previous meta-freescale/EULA overrides
cd $CWD/sources/meta-freescale
if [ -h EULA ]; then
    echo Cleanup meta-freescale/EULA...
    git checkout -- EULA
fi
if [ ! -f classes/fsl-eula-unpack.bbclass ]; then
    echo Cleanup meta-freescale/classes/fsl-eula-unpack.bbclass...
    git checkout -- classes/fsl-eula-unpack.bbclass
fi
cd -

# Override the click-through in meta-freescale/EULA
FSL_EULA_FILE=$CWD/sources/meta-imx/EULA.txt

# Set up the basic yocto environment
if [ -z "$DISTRO" ]; then
   DISTRO=$FSLDISTRO MACHINE=$MACHINE . ./$PROGNAME $BUILD_DIR
else
   MACHINE=$MACHINE . ./$PROGNAME $BUILD_DIR
fi

# Point to the current directory since the last command changed the directory to $BUILD_DIR
BUILD_DIR=.

if [ ! -e $BUILD_DIR/conf/local.conf ]; then
    echo -e "\n ERROR - No build directory is set yet. Run the 'setup-environment' script before running this script to create " $BUILD_DIR
    echo -e "\n"
    return 1
fi

# On the first script run, backup the local.conf file
# Consecutive runs, it restores the backup and changes are appended on this one.
if [ ! -e $BUILD_DIR/conf/local.conf.org ]; then
    cp $BUILD_DIR/conf/local.conf $BUILD_DIR/conf/local.conf.org
else
    cp $BUILD_DIR/conf/local.conf.org $BUILD_DIR/conf/local.conf
fi

echo >> conf/local.conf
echo "# Switch to Debian packaging and include package-management in the image" >> conf/local.conf
echo "PACKAGE_CLASSES = \"package_deb\"" >> conf/local.conf
echo "EXTRA_IMAGE_FEATURES += \"package-management\"" >> conf/local.conf
echo "EXTRA_IMAGE_FEATURES += \"ssh-server-openssh\"" >> conf/local.conf
echo "CORE_IMAGE_EXTRA_INSTALL += \"dotnet-core\"" >> conf/local.conf
echo "CORE_IMAGE_EXTRA_INSTALL += \"aspnet-core\"" >> conf/local.conf
echo "CORE_IMAGE_EXTRA_INSTALL += \"vsdbg\"" >> conf/local.conf
echo "PREFERRED_VERSION_dotnet-core = \"6.0.0\"" >> conf/local.conf
echo "DISTRO_FEATURES:append = \"opengl x11\"" >> conf/local.conf

echo "IMAGE_INSTALL:append = \" \\" >> conf/local.conf
echo "expand-rootfs \\" >> conf/local.conf
echo "linux-firmware \\" >> conf/local.conf
echo "touch-files \\" >> conf/local.conf
echo "bash \\" >> conf/local.conf
echo "jq \\" >> conf/local.conf
echo "safia \\" >> conf/local.conf
echo "init \\" >> conf/local.conf
echo "psplash \\" >> conf/local.conf
echo "influx \\" >> conf/local.conf
echo "influxdb \\" >> conf/local.conf
echo "libgdiplus \\" >> conf/local.conf
echo "matchbox-keyboard \\" >> conf/local.conf
echo "telegraf \\" >> conf/local.conf
echo "gnupg \\" >> conf/local.conf
echo "touch-files \\" >> conf/local.conf
echo "fontconfig \\" >> conf/local.conf
echo "udev \\" >> conf/local.conf
echo "udev-extraconf \\" >> conf/local.conf
echo "ntp \\" >> conf/local.conf
echo "gnuplot \\" >> conf/local.conf
echo "qtbase qtbase-tools qtbase-plugins \\" >> conf/local.conf
echo "\"" >> conf/local.conf

echo "INHERIT += \"rm_work\"" >> conf/local.conf

if [ ! -e $BUILD_DIR/conf/bblayers.conf.org ]; then
    cp $BUILD_DIR/conf/bblayers.conf $BUILD_DIR/conf/bblayers.conf.org
else
    cp $BUILD_DIR/conf/bblayers.conf.org $BUILD_DIR/conf/bblayers.conf
fi


META_FSL_BSP_RELEASE="${CWD}/sources/meta-imx/meta-bsp"

echo "" >> $BUILD_DIR/conf/bblayers.conf
echo "# i.MX Yocto Project Release layers" >> $BUILD_DIR/conf/bblayers.conf
hook_in_layer meta-imx/meta-bsp
hook_in_layer meta-imx/meta-sdk
hook_in_layer meta-imx/meta-ml
hook_in_layer meta-imx/meta-v2x

echo "" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-clang\"" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-dotnet-core\"" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-openembedded/meta-gnome\"" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-openembedded/meta-networking\"" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-openembedded/meta-filesystems\"" >> $BUILD_DIR/conf/bblayers.conf

echo "BBLAYERS += \"\${BSPDIR}/sources/meta-istran\"" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-splash\"" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-mono\"" >> $BUILD_DIR/conf/bblayers.conf
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-qt5\"" >> $BUILD_DIR/conf/bblayers.conf

# Enable docker for mx8 machines
echo "BBLAYERS += \"\${BSPDIR}/sources/meta-virtualization\"" >> conf/bblayers.conf

echo BSPDIR=$BSPDIR
echo BUILD_DIR=$BUILD_DIR

# Support integrating community meta-freescale instead of meta-fsl-arm
if [ -d ../sources/meta-freescale ]; then
    echo meta-freescale directory found
    # Change settings according to environment
    sed -e "s,meta-fsl-arm\s,meta-freescale ,g" -i conf/bblayers.conf
    sed -e "s,\$.BSPDIR./sources/meta-fsl-arm-extra\s,,g" -i conf/bblayers.conf
fi

cd  $BUILD_DIR
clean_up
unset FSLDISTRO
