# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# Unable to find any files that looked like license statements. Check the accompanying
# documentation and source headers and set LICENSE and LIC_FILES_CHKSUM accordingly.
#
# NOTE: LICENSE is being set to "CLOSED" to allow you to at least start building - if
# this is not accurate with respect to the licensing of the software being built (it
# will not be in most cases) you must specify the correct value before using this
# recipe for anything other than initial testing/development!
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "https://dl.influxdata.com/telegraf/releases/telegraf-${PV}_linux_arm64.tar.gz"
SRC_URI[md5sum] = "a3375574ed07501c8f2197a4caa6b6ea"
SRC_URI[sha1sum] = "3f2a8a13446ce06ba89a4641f5c9f1c8c5a4f910"
SRC_URI[sha256sum] = "7bfb5ea3cdeb587a6bfeb02bc3d8ea954ac05dbcd19e1518f228438ea9cbf66e"
SRC_URI[sha384sum] = "462ff6d152b2b0061e9e715c80538917edb5112aef851a51984f8db1fdcc76abde2bd860742d1889b210b7ecb819db6b"
SRC_URI[sha512sum] = "0e5f04a12f5857b8847c87b12e87b95ff289b7a0ecc0252620d13f6cd0c1ed8ee106a0dd9ea859ad3ccb4ed235f382a6ad9cfe129471b7d7d3a0c4e710621a52"

S = "${WORKDIR}/${BPN}-${PV}"

INSANE_SKIP:${PN} += "already-stripped"


# NOTE: no Makefile found, unable to determine what needs to be done


do_install () {
	
    	install -d ${D}${libdir}/telegraf/scripts
    	install -d ${D}${sysconfdir}/logrotate.d
    	install -d ${D}${sysconfdir}/telegraf
    	install -d ${D}${sysconfdir}/telegraf.d
    	install -d ${D}/usr/bin
    	install -d ${D}/etc/systemd/system

    	install -m 0755 ${S}/etc/logrotate.d/telegraf ${D}/etc/logrotate.d/telegraf
  	install -m 0755 ${S}/etc/telegraf/telegraf.conf ${D}/etc/telegraf/telegraf.conf
  	
    	install -m 0755 ${S}/usr/bin/telegraf ${D}/usr/bin/telegraf
    	
    	install -m 0755 ${S}/usr/lib/telegraf/scripts/init.sh ${D}/usr/lib/telegraf/scripts/init.sh
    	install -m 0755 ${S}/usr/lib/telegraf/scripts/telegraf.service ${D}/usr/lib/telegraf/scripts/telegraf.service
    	install -m 0755 ${S}/usr/lib/telegraf/scripts/telegraf.service ${D}/etc/systemd/system/telegraf.service
}

inherit systemd
SYSTEMD_SERVICE_${PN} = "telegraf.service"
