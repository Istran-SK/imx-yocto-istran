DESCRIPTION = "Custom image with Python3 and Qt6, for istran PC"

LICENSE = "MIT"

IMAGE_FEATURES += " \
    package-management \
    ssh-server-openssh \
    debug-tweaks \
    tools-profile \
    tools-sdk \
    package-management \
    splash \
    nfs-server \
    tools-debug \
    ssh-server-openssh \
    tools-testapps \
    hwcodecs \
"

IMAGE_INSTALL += "\
    expand-rootfs e2fsprogs-resize2fs \
    linux-firmware \
    touch-files \
    bash jq psplash psplash-default \
    gnupg ntp \
    udev udev-extraconf \
    sqlite3 \
    \
    qtbase qtdeclarative qttools qtwayland qtsvg qtshadertools \
    qtbase-tools qtbase-plugins qtvirtualkeyboard \
    qtquick3d \
    ca-certificates tzdata \
    procps iproute2 less strace \
    libxkbcommon libinput \
    python3-core python3-modules \
    python3-sqlite3 python3-multiprocessing python3-compression \
    python3-numpy python3-matplotlib python3-pillow \
    python3-typing-extensions python3-idna python3-certifi \
    \
    weston weston-init \
    \
    imx-gpu-viv \
    \
"


# === Python deps from requirements.txt (runtime) ===
IMAGE_INSTALL += " \
    python3-pydantic-core \
    python3-pydantic \
    python3-fastapi \
    python3-starlette \
    python3-httpx \
    python3-httpcore \
    python3-anyio \
    python3-h11 \
    python3-idna \
    python3-sniffio \
    python3-certifi \
    python3-annotated-types \
    python3-aiosqlite \
    python3-python-dotenv \
    python3-pymodbus \
"

# Add to IMAGE_INSTALL
IMAGE_INSTALL += " \
    python3-venv python3-setuptools python3-wheel \
    packagegroup-core-buildessential \ 
"

# Drop things we know we don’t want
IMAGE_INSTALL:remove = "kmscube kmscube-dev packagegroup-fsl-tools-gpu-external"

IMAGE_INSTALL:remove = "kmscube kmscube-dev packagegroup-fsl-tools-gpu-external"

PACKAGECONFIG:append:pn-qtbase = " wayland libinput xkbcommon eglfs kms"

V2X_PKGS = ""
V2X_PKGS:mx8dxl-nxp-bsp = "packagegroup-imx-v2x"

DOCKER ?= ""
DOCKER:mx8-nxp-bsp = "docker"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-imx-isp \
    packagegroup-imx-security \
    firmwared \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
    ${V2X_PKGS} \
    ${DOCKER} \
    ${G2D_SAMPLES} \
"

inherit core-image