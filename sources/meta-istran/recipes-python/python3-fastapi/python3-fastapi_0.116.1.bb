SUMMARY = "FastAPI (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "fastapi-0.116.1-py3-none-any.whl"
RDEPENDS:${PN} += "python3-pydantic python3-starlette python3-typing-extensions"
