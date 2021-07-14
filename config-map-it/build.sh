#!/usr/bin/env bash

# build jar only, no tests, no chart
.././gradlew clean build bootjar -x test -x helmChartBuild --quiet

# validate the chart
# I have disabled some changes in my github account on purpose
# might enable them back at some point in time
# this is not free, currently 1000 request per month are free

IFS=$'\n'
declare -a datree_result

# search anywhere in the path, that weird jq command...
datree_result=$(helm datree test src/main/helm -o json | jq -c 'paths | select(.[-1] == "ErrorMessage")')

if [[ "${datree_result[@]}" =~ "ErrorMessage" ]]
then
   echo "failure validating"
   exit 1
fi

# ${IMAGE} is a skaffold specific env variable. skaffold builds one image with two tags:
# 123-dirty and sha:456. ${IMAGE} env variable that skaffold provides, corresponds to '123-dirty'. Once we know ${IMAGE},
# we can capture the output of 'docker build ...' that is going to be 'sha:456', parse it with that
# awk and get the second image tag (456).
docker_image_sha="$(docker build --quiet --build-arg JAR_FILE='build/libs/*.jar'  -t ${IMAGE} .)"
docker_image_sha_tag="$(echo ${docker_image_sha} | awk '{split($0, a, ":"); print a[2]}')"
echo "image tag : ${IMAGE} , sha tag : ${docker_image_sha_tag}"