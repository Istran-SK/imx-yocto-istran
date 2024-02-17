#!/bin/bash
#init script for VOX panel PC

INFLUX_ORG=ISTRAN
INFLUX_BUCKET=SaFIA
TELEGRAF_CONF_PATH=/etc/telegraf/telegraf.conf

calibrator.sh
adduser --disabled-password influxdb
addgroup influxdb
usermod -aG influxdb influxdb
systemctl enable influxdb
systemctl start influxdb
# Operator token will be generated and saved in influx connection configuration
influx setup --username istran_admin --password istran123 --org $INFLUX_ORG --bucket $INFLUX_BUCKET --force
ORG_TOKEN=$(influx auth create --all-access --host http://localhost:8086 --org $INFLUX_ORG --json | jq -r '.token')
echo "$ORG_TOKEN"
BUCKET_ID=$(influx bucket list --json | jq -r '.[] | select(.name == "SaFIA") | .id')
SAFIA_TOKEN=$(influx auth create --read-bucket "$BUCKET_ID" --write-bucket "$BUCKET_ID" --host http://localhost:8086 --org $INFLUX_ORG --json | jq -r '.token')
touch /etc/profile.d/init_env.sh
echo "#!/bin/sh" >> /etc/profile.d/init_env.sh
echo "export SAFIA_TOKEN=$SAFIA_TOKEN" >> /etc/profile.d/init_env.sh
echo "export ORG_TOKEN=$ORG_TOKEN" >> /etc/profile.d/init_env.sh

#setup telegraf
adduser --disabled-password telegraf
addgroup telegraf
usermod -aG telegraf telegraf

# copy preinstalled config
rm /etc/telegraf/telegraf.conf
cp /etc/telegraf/telegraf.conf.def /etc/telegraf/telegraf.conf
mkdir -p /etc/systemd/system/telegraf.service.d
touch /etc/systemd/system/telegraf.service.d/environment.conf
echo "[Service]" >> /etc/systemd/system/telegraf.service.d/environment.conf
echo "Environment=\"SAFIA_TOKEN=$SAFIA_TOKEN\"" >> /etc/systemd/system/telegraf.service.d/environment.conf
systemctl daemon-reload
systemctl enable telegraf
systemctl restart telegraf

