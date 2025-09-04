#!/bin/bash
# unpack image
mkdir deploy
cp build/tmp/deploy/images/dm421/imx-boot-dm421-sd.bin-flash_evk deploy/imx-boot-dm421-sd.bin-flash_evk
unzstd -f build/tmp/deploy/images/dm421/imx-image-istran-dm421.wic.zst -o deploy/imx-image-istran-dm421.wic
sudo uuu uuu.auto
rm -rf deploy/*
rmdir deploy
