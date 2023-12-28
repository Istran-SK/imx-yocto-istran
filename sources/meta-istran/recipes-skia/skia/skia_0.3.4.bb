# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://git@github.com/AvaloniaUI/skiabuild.git;protocol=ssh;tag=v{PV};"

INSANE_SKIP:${PN} += "already-stripped"

do_compile () {
	make skia_defconfig
	make skia_aarch64_linux-gnu_defconfig
	make docker
	make
}


