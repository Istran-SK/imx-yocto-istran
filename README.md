# Istran Yocto
Based on IMX Yocto: [USER GUIDE (PDF)](https://www.nxp.com/docs/en/user-guide/IMX_YOCTO_PROJECT_USERS_GUIDE.pdf)
- Distro: **xwayland**
- Currently targets only **VOX-070-TS-N8M**
- Yocto release: **kirkstone** (LTS to April 2026)

### Build machine requirements
- Recommended OS: Ubuntu 20.04 or later (building in VM is not recommended)
- At least 120 GB of free space, 250 GB recommended
- 8 core processor recommended for faster build
- For every thread at least 2 GB of RAM is recommended (i.e. for 8 core CPU with 16 threads 32 GB RAM is recommended)

### Build instructions
1. Install dependencies 
```
$ sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential 
chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils \
iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
python3-subunit mesa-common-dev zstd liblz4-tool file locales uuu -y
$ sudo locale-gen en_US.UTF-8
```
2. Clone the project and cd to project directory
```
git clone git@github.com:eldendiss/istran-yocto.git
cd istran-yocto
```

3. Setup environment
```
DISTRO=fsl-imx-xwayland MACHINE=dm421 source imx-setup-release.sh -b build
```
4. Start build
```
bitbake imx-image-multimedia -k
```
This command will build flashable image, by downloading and compiling source packages.

Note: Build time varies from host to host and from internet connection speed. It's not uncommon for initial build to take 10+ hours.

### Uploading image
After succesfull compilation image can be uploaded to target device. Compiled image can be found in build/tmp/deploy/images/dm421 directory. We will need two files with extensions:
```
*.rootfs.wic.zst
*.bin-flash_evk
```
We need to unpack the ZST archive:
```
zstd -d *.rootfs.wic.zst image.wic
```

After that we can upload the image using UUU to target device (device must be in download mode, see ICOP wiki for more info).
```
uuu uuu.auto
```

For easier uploads deploy.sh script is provided, which does the steps above automatically (requires sudo). Device must be in download mode, and connected via USB. After deployment, device can be switched back to MMC boot mode and deployed image will be started:
```
sh deploy.sh
```

After deployment, image needs to be configured. Script is provided to make it easier, however some manual input will be needed. This includes:
- Calibrating touch screen
- Configuring influxdb, telegraf and SaFIA app
- Configuring IP address
- Configuring on-screen keyboard


