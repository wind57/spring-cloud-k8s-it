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
   echo "failure validating : $(helm datree test src/main/helm)"
   exit 1
fi

docker build --quiet --build-arg JAR_FILE='build/libs/*.jar' -t ${IMAGE} .