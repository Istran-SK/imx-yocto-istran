# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "file://init.zip"

FILES:${PN} = "/usr/share/safia/init/* /lib/systemd/system/startup.service"

INSANE_SKIP:${PN} += "already-stripped"

RDEPENDS:${PN} += "bash"

do_install () {
	# Specify install commands here
	install -d ${D}/usr/share/safia/init
	install -d ${D}/lib/systemd/system
    
    	cp -r ${S}/init.sh ${D}/usr/share/safia/init
	cp -r ${S}/telegraf.conf.def ${D}/usr/share/safia/init 
	cp -r ${S}/ntp.conf ${D}/usr/share/safia/init
	cp -r ${S}/startup.service ${D}/lib/systemd/system
    	chmod +x ${D}/usr/share/safia/init/*.sh
	chmod 644 ${D}/lib/systemd/system/startup.service
}

