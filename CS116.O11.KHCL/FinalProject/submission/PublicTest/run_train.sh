#!/bin/bash

train_dir=$1
dev_dir=$2
model_dir=$3

python main.py train --train_dir "$train_dir" --dev_dir "$dev_dir" --model_dir "$model_dir"
