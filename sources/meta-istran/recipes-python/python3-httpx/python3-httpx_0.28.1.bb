SUMMARY = "HTTPX (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "httpx-0.28.1-py3-none-any.whl"
RDEPENDS:${PN} += "python3-httpcore python3-idna python3-sniffio python3-certifi"
