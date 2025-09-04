SUMMARY = "AnyIO (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "anyio-4.9.0-py3-none-any.whl"
RDEPENDS:${PN} += "python3-idna python3-sniffio python3-typing-extensions"
