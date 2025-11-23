FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://BCM43430A1.1DX.hcd \
    file://brcmfmac43430-sdio.icop,imx8mm.txt \
    file://brcmfmac43430-sdio.icop,imx8mm.bin \
"

do_install:append() {
    install -m 0644 ${WORKDIR}/BCM43430A1.1DX.hcd ${D}${sysconfdir}/firmware/BCM43430A1.1DX.hcd
    install -m 0644 ${WORKDIR}/brcmfmac43430-sdio.icop,imx8mm.txt ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac43430-sdio.icop,imx8mm-${MACHINE_ARCH}.txt
    install -m 0644 ${WORKDIR}/brcmfmac43430-sdio.icop,imx8mm.bin ${D}${nonarch_base_libdir}/firmware/brcm/brcmfmac43430-sdio.icop,imx8mm-${MACHINE_ARCH}.bin
}

PACKAGES =+ "${PN}-example"
FILES_${PN}-example = " /etc/firmware/BCM43430A1.1DX.hcd"
