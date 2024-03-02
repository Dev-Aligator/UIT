#!/bin/bash

# Install Python and pip
sudo pacman -S python

# Install pip if it's not installed already
sudo pacman -S python-pip

# Use pip to install packages from requirements.txt
pip install -r requirements.txt --break-system-packages

