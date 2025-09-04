SUMMARY = "aiosqlite (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "aiosqlite-0.21.0-py3-none-any.whl"
RDEPENDS:${PN} += "python3-sqlite3"
