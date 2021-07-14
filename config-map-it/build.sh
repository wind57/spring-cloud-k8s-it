#!/usr/bin/env bash

# find the correct tag for skaffold to be used in "skaffold deploy --image TAG"
#IFS=$' '
#declare -a docker_image
#docker_image_command=$(docker images | grep dirty)
#docker_image=($docker_image_command)
#TAG="${docker_image[1]}"
#echo "$TAG" >> tag.txt

# build jar only, no tests, no chart

#TODO
.././gradlew clean build bootjar -x test -x helmChartBuild --quiet

# validate the chart
# I have disabled some changes in my github account on purpose
# might enable them back at some point in time
# this is not free, currently 1000 request per month are free

#TODO
#IFS=$'\n'
#declare -a datree_result

# search anywhere in the path, that weird jq command...

#TODO
#datree_result=$(helm datree test src/main/helm -o json | jq -c 'paths | select(.[-1] == "ErrorMessage")')
#
#if [[ "${datree_result[@]}" =~ "ErrorMessage" ]]
#then
#   echo "failure validating"
#   exit 1
#fi

echo ${IMAGE}

# ${IMAGE} is a skaffold specific env variable, this is needed so that
# a correctly tagged image is build
docker build --build-arg JAR_FILE='build/libs/*.jar'  -t ${IMAGE} .

# load docker image into kind (in the proper namespace), this is sort of like doing :
# "eval (minikube docker-env)" in case of minikube
#kind load docker-image ${IMAGE} --name spring-k8s