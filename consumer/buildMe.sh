#!/bin/bash
./gradlew clean
./gradlew build
docker build  -t kafka-consumer-example:1.1 .