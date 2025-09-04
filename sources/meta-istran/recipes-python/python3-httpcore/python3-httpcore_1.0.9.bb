SUMMARY = "HTTPCore (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "httpcore-1.0.9-py3-none-any.whl"
RDEPENDS:${PN} += "python3-h11 python3-anyio python3-idna python3-sniffio python3-certifi"
