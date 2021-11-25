#!/bin/bash

# $1: Wifi SSID
# $2: Wifi Password
SetupKnownWifi() {

	if grep -q "ssid=$1" "/etc/wpa_supplicant.conf"; then
		echo "Wifi with SSID $1 already known"
	else
		wpa_passphrase $1 $2 | sudo tee /etc/wpa_supplicant.conf
	fi

}
