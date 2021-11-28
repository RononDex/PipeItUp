#!/bin/bash

# $1: Wifi SSID
# $2: Wifi Password
SetupKnownWifi() {

	if grep -q "ssid=$1" "/etc/wpa_supplicant/wpa_supplicant.conf"; then
		echo "Wifi with SSID $1 already known"
	else
		wpa_passphrase "$1" "$2" | sudo tee -a /etc/wpa_supplicant/wpa_supplicant.conf
	fi

}

SetupAutoHotspot() {
	cd ~/packages
	if [ ! -d ./AutoHotpost-Installer ]; then
		git clone https://github.com/RaspberryConnect/AutoHotspot-Installer
		cd AutoHotspot-Installer
	else
		cd AutoHotspot-Installer
		git pull
	fi

	cd ~/packages/AutoHotspot-Installer/AutoHotspot-Setup/Autohotspot/
	bash ./autohotspot-setup.sh
}
