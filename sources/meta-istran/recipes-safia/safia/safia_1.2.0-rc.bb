# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRCREV = "4cb29bfc8379b64e7ac29f615dcbd995504d6cb1"
SRC_URI = "file://safia_1.2.0-rc.zip"

INSANE_SKIP:${PN} += "already-stripped file-rdeps libdir"

DEPENDS += "fontconfig"

do_install () {
	# Specify install commands here
	install -d ${D}/usr/share/safia
    
    	cp -r ${S}/*.* ${D}/usr/share/safia
    	chmod +x ${D}/usr/share/safia/*.dll
}

