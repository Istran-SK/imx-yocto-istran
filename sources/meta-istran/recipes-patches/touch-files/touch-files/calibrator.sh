str=$(weston-touch-calibrator -v "DSI-1")
substr=$(echo $str | cut -c21-)
echo 'SUBSYSTEM=="input", 
KERNEL=="event[0-9]*",ENV{ID_INPUT_TOUCHSCREEN}=="1",ENV{LIBINPUT_CALIBRATION_MATRIX}="'$substr'"' > /etc/udev/rules.d/touchscreen.rules

