FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += " \
    file://BCM43430A1.1DX.hcd \
"

do_install_append() {
    install -m 0644 ${WORKDIR}/BCM43430A1.1DX.hcd ${D}${sysconfdir}/firmware
}

PACKAGES =+ "${PN}-example"
FILES_${PN}-example = " /etc/firmware/BCM43430A1.1DX.hcd"
