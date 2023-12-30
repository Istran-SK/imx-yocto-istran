# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "CLOSED"
#LIC_FILES_CHKSUM = "file://usr/share/influxdb/LICENSE;md5=86f93342918318318c795b9999bd8131"

SRC_URI = "https://dl.influxdata.com/influxdb/releases/influxdb2-client-${PV}-linux-arm64.tar.gz"
SRC_URI[md5sum] = "34d565f6100295ce62ad791dd4cf43cf"
SRC_URI[sha1sum] = "9cfdbaa671f2a96418ec0b099485a01f0073cd03"
SRC_URI[sha256sum] = "d5d09f5279aa32d692362cd096d002d787b3983868487e6f27379b1e205b4ba2"
SRC_URI[sha384sum] = "c3960a09c2acf4768baff64138d2b9dc8405389fa10e85bc33813b7889561f1bc91571b24a835c3fc573b3b4dbaa0e8a"
SRC_URI[sha512sum] = "03719fe1cc8c5d2eac6c97b7c0412bfc466b3f49696677c267a61cf7ded5e39aad31985b57b2d6a12062f2da446a8ec85a07df3fbbb3378dd93dc39567953fe5"

S = "${WORKDIR}"

INSANE_SKIP:${PN} += "already-stripped"


do_install () {
	
    	install -d ${D}/usr/bin

    	install -m 0755 ${S}/influx ${D}/usr/bin/influx
}
