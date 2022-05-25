#!/bin/bash

# This file is used to setup a raspbian based raspberry pi for the pipe it up project.
# It installs all necessary software and installs the game from source.
#
#	Will require sudo password

REPOSITORY_URL="https://github.com/RononDex/PipeItUp"


scriptDir="$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"

sudo apt update
sudo apt upgrade -y
sudo apt install openjdk-11-jdk git swig maven openjfx gradle -y

# ---------------------------------------------------------------------------------
# Ensure git repo is cloned and that pwd is inside the cloned / up to date git repo
# ---------------------------------------------------------------------------------
if git rev-parse --git-dir > /dev/null 2>&1; then
	# We are inside a git repo
	git pull

else
	mkdir -p ~/packages >/dev/null 2>&1

	cd ~/packages
	if [ -d ~/packages/PipeItUp ]; then
		cd ~/packages/PipeItUp
		git pull
	else
		git clone $REPOSITORY_URL
		cd PipeItUp/pi
	fi
fi



# ---------------------------------------------------------------------------------
# Setup sudoers config and autostart of game
# ---------------------------------------------------------------------------------
sudo cp "$scriptDir"/sudoers /etc/sudoers
sudo cp "$scriptDir"/pipeitup.desktop /etc/xdg/autostart/pipeitup.desktop

# ---------------------------------------------------------------------------------
# Setup GPIO access for user pipeitup
# ---------------------------------------------------------------------------------
sudo usermod -a -G gpio pipeitup


# ---------------------------------------------------------------------------------
# Configure Raspi-Config
# ---------------------------------------------------------------------------------
sudo raspi-config nonint do_hostname pipe-it-up-pi
sudo raspi-config nonint do_i2c 0 # Enables I2C Interface
sudo raspi-config nonint do_boot_behaviour B4 # Enables Desktop Autologin for touchscreen
sudo raspi-config nonint do_vnc 0 # Activate VNC Server

# ---------------------------------------------------------------------------------
# jar file for LEDs
# ---------------------------------------------------------------------------------
git clone https://github.com/rpi-ws281x/rpi-ws281x-java
cp createNativeLib.sh rpi-ws281x-java/src/scripts/createNativeLib.sh
cd rpi-ws281x-java
sudo bash src/scripts/createNativeLib.sh
gradle wrapper --gradle-version=5.6
./gradlew assemble -x signArchives
cp build/libs/rpi-ws281x-java-2.0.0-SNAPSHOT.jar ../../PipeItUp/repository/com/github/mbelling/rpi-ws281x/2.0.0-SNAPSHOT/rpi-ws281x-2.0.0-SNAPSHOT.jar
sudo chown pipeitup:pipeitup ../../PipeItUp/repository/com/github/mbelling/rpi-ws281x/2.0.0-SNAPSHOT/rpi-ws281x-2.0.0-SNAPSHOT.jar

# ---------------------------------------------------------------------------------
# Setup wifi Mgmt
# ---------------------------------------------------------------------------------

sudo rm /etc/wpa_supplicant/wpa_supplicant.conf

echo "country=CH" | sudo tee -a /etc/wpa_supplicant/wpa_supplicant.conf
echo "ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev" | sudo tee -a /etc/wpa_supplicant/wpa_supplicant.conf
echo "update_config=1" | sudo tee -a /etc/wpa_supplicant/wpa_supplicant.conf

SetupKnownWifi "Pipe-It-Up-Internet" "pipe-it-up!3"
