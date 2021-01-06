#!/bin/bash
cd consumer
source buildMe.sh
cd ../producer
source buildMe.sh
docker-compose up -d

