# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://usr/share/influxdb/LICENSE;md5=86f93342918318318c795b9999bd8131"

SRC_URI = "https://dl.influxdata.com/influxdb/releases/influxdb2-${PV}_linux_arm64.tar.gz"
SRC_URI += "file://influxdb.conf"
SRC_URI[md5sum] = "515da27004fa21dae5b247a2f4c2f845"
SRC_URI[sha1sum] = "e86f02bf4468a13e781a6fbc5b7bc72e52a78997"
SRC_URI[sha256sum] = "d3c64a73bd8f1a5557e3917a580030af77b71df9d0483c15ef07a3645b29fc60"
SRC_URI[sha384sum] = "334d26eaa16cb7673f4100bac65318ad66395bd4f354589f05eded20cc61a9fffb565a4c9c1c51d1df71b4b3071c1fd1"
SRC_URI[sha512sum] = "3f7cf6322d24575aaac8c2907d5b0c3800bc14a73969316d920a0a3b97fa4f536101beab8f9240afec1ba2b5b8316abd23e7f5d09d9cec492b378d44f1bb6c08"

S = "${WORKDIR}/${BPN}2-${PV}"

INSANE_SKIP:${PN} += "already-stripped"

RDEPENDS:${PN} += "bash"

do_install () {
	
    	install -d ${D}/usr/lib/influxdb/scripts
    	install -d ${D}/etc/logrotate.d
    	install -d ${D}/usr/share/influxdb
    	install -d ${D}/usr/bin
    	install -d ${D}/etc/systemd/system
    	install -d ${D}/etc/influxdb

    	install -m 0755 ${S}/usr/lib/influxdb/scripts/influxdb.service ${D}/usr/lib/influxdb/scripts/influxdb.service
    	install -m 0755 ${S}/usr/lib/influxdb/scripts/influxd-systemd-start.sh ${D}/usr/lib/influxdb/scripts/influxd-systemd-start.sh
    	install -m 0755 ${S}/usr/lib/influxdb/scripts/init.sh ${D}/usr/lib/influxdb/scripts/init.sh
    	install -m 0755 ${S}/usr/share/influxdb/influxdb2-upgrade.sh ${D}/usr/share/influxdb/influxdb2-upgrade.sh
    	install -m 0755 ${S}/etc/logrotate.d/influxdb ${D}/etc/logrotate.d/influxdb
    	install -m 0755 ${S}/usr/bin/influxd ${D}/usr/bin/influxd
    	install -m 0755 ${S}/usr/lib/influxdb/scripts/influxdb.service ${D}/etc/systemd/system/influxdb.service
	install -m 0664 ${S}/../influxdb.conf ${D}/etc/influxdb/influxdb.conf
}

inherit systemd
SYSTEMD_SERVICE_${PN} = "influxdb.service"
