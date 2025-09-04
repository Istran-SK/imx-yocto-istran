SUMMARY = "Pydantic v2 (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "pydantic-2.11.7-py3-none-any.whl"
RDEPENDS:${PN} += "python3-pydantic-core python3-annotated-types python3-typing-extensions"
