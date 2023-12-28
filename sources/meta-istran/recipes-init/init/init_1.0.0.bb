# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://init.sh"

INSANE_SKIP:${PN} += "already-stripped"

do_install () {
	# Specify install commands here
	install -d ${D}/home
    
    	cp -r ${S}/*.sh ${D}/home
    	chmod +x ${D}/home/*.sh
}

