#!/bin/bash

# error handling
set -o errexit;
set -o pipefail;
set -o nounset;
# debug commands
#set -x;

# a version that will be used to build the spring-cloud-kubernetes project
# used only for integration-tests
SPRING_CLOUD_KUBERNETES_VERSION="100.100.100-SNAPSHOT"

#path of the spring-cloud-kubernetes-project
SPRING_CLOUD_KUBERNETES_PATH=/Users/erabii784/Documents/personal/spring/spring-cloud-kubernetes

#global array of gradle subprojects
declare -a subprojects_array

#global array of clusters
declare -a clusters

build_spring_cloud_kubernetes_project() {
  echo "starting to build spring-cloud-kubernetes project"
  local tmp_dir
  tmp_dir="$(mktemp -d)"
  cp -r $SPRING_CLOUD_KUBERNETES_PATH $tmp_dir

  # needs special handling because of the way spring-cloud-kubernetes project is set-up
  cd $tmp_dir/spring-cloud-kubernetes/spring-cloud-kubernetes-dependencies
  mvn versions:set -DgenerateBackupPoms=false -DnewVersion=$SPRING_CLOUD_KUBERNETES_VERSION

  cd ..

  mvn versions:set -DgenerateBackupPoms=false -DnewVersion=$SPRING_CLOUD_KUBERNETES_VERSION
  mvn clean install -DskipTests

}

kind_setup() {
  # see if kind is already installed, if not : do it
  local kind_install
  kind_install="$(command -v kind || true)"
  if [[ $kind_install == "" ]]
  then
    brew install kind
  else
    echo "kind already installed"
  fi

  # see what clusters are already present, delete/re-create spring-k8s if needed
  local what_clusters;
  what_clusters="$(kind get clusters)";
  IFS=$'\n'
  clusters=($what_clusters);

  if [[ "${clusters[@]}" =~ "spring-k8s" ]]
  then
    echo "cluster spring-k8s already present"
    #TODO
    #kind delete cluster --name spring-k8s
    #kind create cluster --name spring-k8s --wait 10m
  else
    # delete/create
    kind create cluster --name spring-k8s --wait 10m
  fi
}

uninstall_kind() {
  kind delete cluster --name spring-k8s
  brew uninstall kind
}

gradle_list_subprojects() {
  cd $SPRING_CLOUD_KUBERNETES_PATH
  cd ..
  cd spring-cloud-kubernetes-it
  local subprojects
  subprojects="$(gradle findSubProjects --quiet)"

  IFS=$'\n'
  subprojects_array=($subprojects)
}

## this assumes helm and skaffold are installed. At some point I will validate this and make it nicer
main() {

  ################################# 0 #################################
  kind_setup

  ################################# 1 #################################
  #TODO
  #build_spring_cloud_kubernetes_project

  ################################# 2 #################################
  gradle_list_subprojects

  for i in "${subprojects_array[@]}"
  do

    ################################# 3 #################################
    # docker remove the previous image, if it exists
    local x
    x=$(docker images | grep -c zero-x/spring-cloud-kubernetes/$i || true)
    if [ "0" -eq "$x" ]
    then
      echo "image : zero-x/spring-cloud-kubernetes/$i not present"
    else
      echo "deleting image : zero-x/spring-cloud-kubernetes/$i"
      docker rmi zero-x/spring-cloud-kubernetes/$i
    fi

    local current_dir="$(pwd)"
    local proj_dir="$current_dir/$i"
    cd $proj_dir

  ################################# 4 #################################
    chmod +x build.sh
    skaffold build

  # load docker image into kind, this is sort of like doing :
  # "eval (minikube docker-env)" in case of minikube
  ################################# 5 #################################
    kind load docker-image zero-x/spring-cloud-kubernetes/$i --name spring-k8s
    cd ..

  done

  ################################# 6 #################################
  #uninstall_kind

}

main