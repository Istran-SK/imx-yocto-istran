DESCRIPTION = "Custom image with Python3 and Qt6, for istran PC"

LICENSE = "MIT"

IMAGE_FEATURES += " \
    package-management \
    ssh-server-openssh \
    debug-tweaks \
    tools-profile \
    tools-sdk \
    package-management \
    splash \
    nfs-server \
    tools-debug \
    ssh-server-openssh \
    tools-testapps \
    hwcodecs \
"

IMAGE_INSTALL += "\
    expand-rootfs e2fsprogs-resize2fs \
    linux-firmware \
    touch-files \
    bash jq psplash psplash-default \
    gnupg ntp \
    udev udev-extraconf \
    \
    qtbase qtdeclarative qttools qtwayland qtsvg qtshadertools \
    qtbase-tools qtbase-plugins qtvirtualkeyboard \
    qtquick3d \
    \
    weston weston-init \
    \
    imx-gpu-viv \
    \
"
# Drop things we know we don’t want
IMAGE_INSTALL:remove = "kmscube kmscube-dev packagegroup-fsl-tools-gpu-external"

IMAGE_INSTALL:remove = "kmscube kmscube-dev packagegroup-fsl-tools-gpu-external"

V2X_PKGS = ""
V2X_PKGS:mx8dxl-nxp-bsp = "packagegroup-imx-v2x"

DOCKER ?= ""
DOCKER:mx8-nxp-bsp = "docker"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-imx-isp \
    packagegroup-imx-security \
    firmwared \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
    ${V2X_PKGS} \
    ${DOCKER} \
    ${G2D_SAMPLES} \
"

inherit core-image