FILESEXTRAPATHS:prepend := "${THISDIR}:"

SRC_URI:append:dm421 = " \
    file://0001-Port-to-VOX.patch \
    file://dm421_defconfig \
"

do_copy_defconfig:append:dm421() {
    cp ${WORKDIR}/dm421_defconfig ${WORKDIR}/defconfig
    cp ${WORKDIR}/dm421_defconfig ${B}/.config
}
