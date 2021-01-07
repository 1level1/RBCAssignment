#!/bin/bash
docker-compose down
./gradlew clean
./gradlew build
docker build  -t kafka-producer-example:1.1 .
docker-compose up -d