#!/bin/bash

# error handling
set -o errexit;
set -o pipefail;
set -o nounset;
set +e;
# debug commands
#set -x;

#CREATE KEYSPACE IF NOT EXISTS Killer_Video WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

LIQUIBASE_4_4_3_ZIP_URL="https://github.com/liquibase/liquibase/releases/download/v4.4.3/liquibase-4.4.3.zip"
#these require periodic updates?
SIMBA_DRIVER_URL="https://downloads.datastax.com/jdbc/cql/2.0.8.1009/SimbaCassandraJDBC42-2.0.8.1009.zip"
LIQUIBASE_CASSANDRA_EXTENSION="https://github.com/liquibase/liquibase-cassandra/releases/download/liquibase-cassandra-4.4.3/liquibase-cassandra-4.4.3.jar"

PASSWORD=""
USERNAME=""
CHANGE_LOG_FILE=""
URL=""
CHANGE_LOG_TABLE_NAME=""

main() {
	validate_arguments
	print_arguments
# these have been moved to the image itself
#	liquibase_tmp_dir
#	download_and_unzip_liquibase
#	download_and_install_simba_driver
#	download_and_install_liquibase_cassandra_extension
#	liquibase_status_check
	liquibase_update
}

##############################################################################################################
############################################## Liquibase status check ########################################
##############################################################################################################
liquibase_status_check() {
	./liquibase --username $USERNAME \
	            --password $PASSWORD \
	            --url $URL \
	            --changelog-file $CHANGE_LOG_FILE \
	            --log-level INFO \
	            --database-changelog-table-name $CHANGE_LOG_TABLE_NAME \
	            --driver com.simba.cassandra.jdbc42.Driver \
	            status

	check_latest_code "'liquibase status ...' completed" $?
}

##############################################################################################################
############################################## Liquibase status check ########################################
##############################################################################################################
liquibase_update() {
	./liquibase --username $USERNAME \
	            --password $PASSWORD \
	            --url $URL \
	            --changelog-file $CHANGE_LOG_FILE \
	            --log-level INFO \
	            --database-changelog-table-name $CHANGE_LOG_TABLE_NAME \
	            --driver com.simba.cassandra.jdbc42.Driver \
	            --headless true \
	            update

	check_latest_code "'liquibase status ...' completed" $?
}


##############################################################################################################
################################### Helper to check the latest result ########################################
##############################################################################################################
print_arguments() {
	printf "####################################################################\n"
	printf "############################ arguments #############################\n"
	printf "Password: '%s'\r\n" "$PASSWORD"
	printf "Username: '%s'\r\n" "$USERNAME"
	printf "URL: '%s'\r\n" "$URL"
	printf "change log file: '%s'\r\n" "$CHANGE_LOG_FILE"
	printf "change log table name '%s'\r\n" "$CHANGE_LOG_TABLE_NAME"
	printf "####################################################################\n"
}

##############################################################################################################
################################### Helper to check the latest result ########################################
##############################################################################################################
validate_arguments() {
	if [ -z $PASSWORD ] || [ -z $USERNAME ] || [ -z $URL ] || [ -z $CHANGE_LOG_FILE ] || [ -z $CHANGE_LOG_TABLE_NAME ]; then
    show_usage
    exit 1
  fi
}


##############################################################################################################
################################### Helper to check the latest result ########################################
##############################################################################################################
check_latest_code() {
	if [[ $2 == 0 ]]; then
		echo "$1 successfully"
	else
		echo "Failure to $1 with code : $?"
		exit 1
	fi
}

##############################################################################################################
######################################## create tmp dir for liquibase ########################################
##############################################################################################################
liquibase_tmp_dir() {
	local tmp_dir
	tmp_dir="$(mktemp -d)"
	liquibase_tmp_dir="$tmp_dir/liquibase"
	mkdir $liquibase_tmp_dir
	echo "dir: $liquibase_tmp_dir"
	cp $CHANGE_LOG_FILE $liquibase_tmp_dir
	cd $liquibase_tmp_dir
}


##############################################################################################################
######################################## Download and unzip liquibase ########################################
##############################################################################################################
download_and_unzip_liquibase() {
	curl -LO -s $LIQUIBASE_4_4_3_ZIP_URL
	check_latest_code "Download liquibase" $?

	find . -name 'liquibase*.zip' -type f -exec unzip -qq {} \;
	check_latest_code "Unzip liquibase" $?
}

##############################################################################################################
################################### Download and install Simba Driver ########################################
##############################################################################################################
download_and_install_simba_driver() {
	curl -LO -s $SIMBA_DRIVER_URL
	check_latest_code "Download Simba driver" $?

	find . -name 'SimbaCassandraJDB*.zip' -type f -exec unzip -qq {} \;
	check_latest_code "Unzip Simba Driver" $?

  	find . -name 'CassandraJDBC*.jar' -type f -exec cp {} lib \;
  	check_latest_code "Install Simba Driver" $?
}

##############################################################################################################
################################### Download and install Liquibase Cassandra Extension #######################
##############################################################################################################
download_and_install_liquibase_cassandra_extension() {
	curl -LO -s $LIQUIBASE_CASSANDRA_EXTENSION
	check_latest_code "Download Extension" $?

	# can say : "... are identical (not copied)", which is not an error
  find . -name 'liquibase-cassandra*.jar' -type f -exec cp -i {} lib \;
  check_latest_code "Install Liquibase Cassandra extension" $?
}


################################### Parse Input Arguments of the Script ######################################
##############################################################################################################
##############################################################################################################
show_usage() {
  echo 'Usage:'
  printf "%s \r\n\t-p    | --password (required). \r\n" "$0"
  printf "\t"
  echo '-user | --username (required).'
  printf "\t"
  echo '-u    | --url (required).'
  printf "\t"
  echo '-f    | --file (required).'
}

##############################################################################################################
################################### Parse Input Arguments of the Script ######################################
##############################################################################################################

positional=()
while [[ $# -gt 0 ]]; do
	arg="$1"
	case ${arg} in

		-p | --password    )          PASSWORD=$2
                                  shift 2
                                  ;;

        -user | --username )      USERNAME=$2
                                  shift 2
                                  ;;

        -u | --url         )      URL=$2
                                  shift 2
                                  ;;

        -f | --file        )      CHANGE_LOG_FILE=$2
                                  shift 2
                                  ;;

        -tn | --table-name )      CHANGE_LOG_TABLE_NAME=$2
                                  shift 2
                                  ;;

        -h | --help| \?    )      show_usage
                                  exit 0
                                  ;;

        *                  )      positional+=("$1")
                                  shift
                                  ;;

    esac
done


main

# locally I had to change

# listen_address: [node-ip]
#seed_provider:
#  - class_name: ...
#    - seeds: "[node-ip]"