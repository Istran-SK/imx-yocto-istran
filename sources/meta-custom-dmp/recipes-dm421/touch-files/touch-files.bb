DESCRIPTION = "Copy EETI touch files"
LICENSE = "CLOSED"

SRC_URI = "\
        file://calibrator.sh \
        file://calibration.rules \
"

S = "${WORKDIR}"

RDEPENDS:${PN} = "libstdc++"

FILES:${PN} = "\
        /usr/bin/calibrator.sh \
        /etc/udev/rules.d/calibration.rules \
"

do_install(){
	install -d ${D}/usr/bin
        install -d ${D}/etc/udev/rules.d/
	install -m 0755 ${WORKDIR}/calibrator.sh ${D}/usr/bin
	install -m 0644 ${WORKDIR}/calibration.rules ${D}${sysconfdir}/udev/rules.d
}

INSANE_SKIP:${PN} = "ldflags"
