SUMMARY = "python-dotenv (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
# Note underscore in wheel name
PY_WHEEL_FILE = "python_dotenv-1.1.1-py3-none-any.whl"
