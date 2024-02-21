# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://safia-${PV}-linux-arm64.zip"

INSANE_SKIP:${PN} += "already-stripped file-rdeps libdir"

DEPENDS += "fontconfig"


do_install () {
	# Specify install commands here
	install -d ${D}/usr/share/safia
    
    	cp -r ${S}/*.* ${D}/usr/share/safia
    	chmod +x ${D}/usr/share/safia/*.dll
}

