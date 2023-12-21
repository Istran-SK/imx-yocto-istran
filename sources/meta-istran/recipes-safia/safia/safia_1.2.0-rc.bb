# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file:///home/dendo/imx-yocto-bsp/sources/meta-istran/recipes-safia/safia/safia-${PV}-linux-arm64.zip"

INSANE_SKIP:${PN} += "already-stripped"

DEPENDS += "fontconfig"
RDEPENDS_${PN} = "libfontconfig.so.1()(64bit)"

do_install () {
	# Specify install commands here
	install -d ${D}/usr/share/safia
	install -d ${D}/usr/lib/safia
    
    	cp -r ${S}/*.* ${D}/usr/share/safia
    	rm -rf ${D}/usr/share/safia/*.so
    	cp -r ${S}/*.so ${D}/usr/lib/safia
    	chmod +x ${D}/usr/share/safia/*.dll
}

