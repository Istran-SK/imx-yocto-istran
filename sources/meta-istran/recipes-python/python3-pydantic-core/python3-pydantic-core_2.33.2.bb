SUMMARY = "pydantic-core (prebuilt wheel)"
LICENSE = "CLOSED"

inherit python_wheel

# Replace with your exact wheel if arch/ABI differ
SRC_URI = "file://${PY_WHEEL_FILE};unpack=0"
PY_WHEEL_FILE = "pydantic_core-2.33.2-cp310-cp310-manylinux_2_17_aarch64.manylinux2014_aarch64.whl"
