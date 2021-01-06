#!/bin/bash
./gradlew clean
./gradlew build
docker build  -t kafka-producer-example:1.1 .