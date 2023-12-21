FILESEXTRAPATHS_prepend := "${THISDIR}/patches:"

SRC_URI_append_dm421 += " \
    file://0001-u-boot-dm421.patch \
"
