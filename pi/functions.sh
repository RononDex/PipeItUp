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
	mkdir -p ~/packages
	cd ~/packages
	rm ./AutoHotspot-Setup.tar.gz
	rm -rf ./Autohotspot
	curl "https://www.raspberryconnect.com/images/hsinstaller/AutoHotspot-Setup.tar.gz" -o AutoHotspot-Setup.tar.gz
	tar -xzvf AutoHotspot-Setup.tar.gz
	cd Autohotspot
	sudo ./autohotspot-setup.sh
}
