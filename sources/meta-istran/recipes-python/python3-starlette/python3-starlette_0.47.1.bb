SUMMARY = "Starlette (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "starlette-0.47.1-py3-none-any.whl"
RDEPENDS:${PN} += "python3-anyio python3-typing-extensions"
