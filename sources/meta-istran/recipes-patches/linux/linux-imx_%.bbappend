FILESEXTRAPATHS:prepend := "${THISDIR}/patches:"

SRC_URI += " \
    file://0001-linux-dm421.patch \
    file://dm421_defconfig \
"

do_copy_defconfig:append:dm421() {
    cp ${WORKDIR}/dm421_defconfig ${WORKDIR}/defconfig
    cp ${WORKDIR}/dm421_defconfig ${B}/.config
}
