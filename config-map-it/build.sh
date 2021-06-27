#!/usr/bin/env bash

gradle clean build -x test --quiet
docker build --build-arg JAR_FILE='build/libs/*.jar'  -t zero-x/spring-cloud-kubernetes/config-map-it .