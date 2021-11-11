#!/bin/bash

# This file is used to setup a raspbian based raspberry pi for the pipe it up project.
# It installs all necessary software and installs the game from source.
#
#	Will require sudo password

REPOSITORY_URL="git@gitlab.fhnw.ch:ip12-21vt/ip12-21vt_pipelinesystem/pipe-it-up.git"

sudo apt update
sudo apt upgrade -y
sudo apt install openjdk-11-jdk git -y

if git rev-parse --git-dir > /dev/null 2>&1; then
	# We are inside a git repo
	git pull
else
	# NOT a git repo!
	if [ ! -f ~/.ssh/id_rsa ]
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
	if [ -d ~/packages/pipe-it-up ]
		cd ~/packages/pipe-it-up
		git pull
	else
		git clone $REPOSITORY_URL
		cd pipe-it-up/pi
	fi
fi

cd pi
sudo cp sshd_config /etc/ssh/sshd_config

sudo systemctl restart sshd
sudo systemctl enable sshd

# TODO: VNC Server installieren & konfigurieren
# TODO: pipe-it-up kompillieren und installieren
