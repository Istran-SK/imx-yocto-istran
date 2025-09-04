SUMMARY = "certifi (prebuilt wheel)"
LICENSE = "CLOSED"
inherit python_wheel
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
# Note the calendar version in filename
PY_WHEEL_FILE = "certifi-2025.7.14-py3-none-any.whl"
