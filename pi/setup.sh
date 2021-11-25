#!/bin/bash

# This file is used to setup a raspbian based raspberry pi for the pipe it up project.
# It installs all necessary software and installs the game from source.
#
#	Will require sudo password

REPOSITORY_URL="git@gitlab.fhnw.ch:ip12-21vt/ip12-21vt_pipelinesystem/pipe-it-up.git"

sudo apt update
sudo apt upgrade -y
sudo apt install openjdk-11-jdk git -y

# ---------------------------------------------------------------------------------
# Ensure git repo is cloned and that pwd is inside the cloned / up to date git repo
# ---------------------------------------------------------------------------------
if git rev-parse --git-dir > /dev/null 2>&1; then
	# We are inside a git repo
	git pull
else if [ -f ./autohotspot ]; then
	echo "Working in offline mode and presuming this script was called from inside a copy of the source"
else
	# NOT a git repo!
	if [ ! -f ~/.ssh/id_rsa ]; then
		echo "No existing ssh certificate found, generating one now...."
		ssh-keygen -q -N '' -t rsa -b 4096 -f ~/.ssh/id_rsa <<<y >/dev/null 2>&1

		echo "SSH Key generated, please whitelist the following ssh public key in your git repo:"
		cat ~/.ssh/id_rsa.pub

		echo ""
		echo "-------------------------------------------------------------"
		read  -n 1 -p "Press any key to continue" mainmenuinput
	fi

	mkdir -p ~/packages >/dev/null 2>&1

	cd ~/packages
	if [ -d ~/packages/pipe-it-up ]; then
		cd ~/packages/pipe-it-up
		git pull
	else
		git clone $REPOSITORY_URL
		cd pipe-it-up/pi
	fi
fi

. ./functions.sh

# ---------------------------------------------------------------------------------
# Setup SSH-Daemon
# ---------------------------------------------------------------------------------
cd pi
sudo cp sshd_config /etc/ssh/sshd_config

sudo systemctl restart sshd
sudo systemctl enable sshd

# ---------------------------------------------------------------------------------
# Setup wifi Mgmt
# ---------------------------------------------------------------------------------
sudo apt-get install hostapd dnsmasq -y

sudo systemctl unmask hostapd
sudo systemctl disable hostapd
sudo systemctl disable dnsmasq

sudo mkdir -p /etc/hostapd
sudo cp ./hostapd.conf /etc/hostapd/hostapd.conf
sudo cp ./defaulthostapd /etc/default/hostapd
sudo cp ./dnsmasq.conf /etc/dnsmasq.conf
sudo cp ./interfaces /etc/network/interfaces
sudo cp ./dhcpd.conf /etc/dhcpcd.conf
sudo cp ./autohotspot.service /etc/systemd/system/autohotspot.service
sudo cp ./autohotspot /usr/bin/autohotspot
sudo chmod +x /usr/bin/autohotspot

sudo systemctl enable autohotspot.service

sudo crontab ./cronConfig

SetupKnownWifi "luca" "asdfghjkl"
SetupKnownWifi "Chuck Norris is here" "aafa4e8f08e7"
SetupKnownWifi "Pipe-It-Up" "pipe-it-up!3"

# TODO: VNC Server installieren & konfigurieren
# TODO: pipe-it-up kompillieren und installieren
