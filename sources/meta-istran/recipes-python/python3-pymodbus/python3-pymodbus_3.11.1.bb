SUMMARY = "pymodbus (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "pymodbus-3.11.1-py3-none-any.whl"
RDEPENDS:${PN} += "python3-pyserial"
