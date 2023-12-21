DESCRIPTION = "Expand rootfs partition service"
LICENSE = "CLOSED"

SRC_URI = "\
	file://expand-rootfs \
	file://expand-rootfs.service \
"

S = "${WORKDIR}"

inherit systemd

RDEPENDS_${PN} = "bash whiptail parted util-linux-sfdisk util-linux-partx util-linux-findmnt util-linux-lsblk e2fsprogs-e2fsck e2fsprogs-resize2fs"
SYSTEMD_PACKAGES = "${PN}"

SYSTEMD_SERVICE_${PN} = "expand-rootfs.service"

FILES_${PN} = "\
		${systemd_system_unitdir}/expand-rootfs.service \
		/usr/sbin/expand-rootfs \
"

do_install() {
	install -d ${D}${systemd_system_unitdir}
	install -d ${D}/usr/sbin
	install -m 0755 ${WORKDIR}/expand-rootfs ${D}/usr/sbin/
	install -m 0644 ${WORKDIR}/expand-rootfs.service ${D}${systemd_system_unitdir}/
}
