#!/usr/bin/env bash
cwd=$(pwd)
now=$(date +"%Y_%m_%d")
SERVICE_NAME=faker
PID_PATH_NAME=${cwd}/etc/${SERVICE_NAME}.pid
ERROR_LOG=log/${SERVICE_NAME}-${now}.err.log
OUT_LOG=log/${SERVICE_NAME}-${now}.out.log

run() {
    mvn package
    java -jar -Dlogback.configurationFile=$1 target/faker-0.1.jar $2
}

start() {
    echo "Starting ${SERVICE_NAME} ..."
    if [ ! -f ${PID_PATH_NAME} ]; then
        nohup java -jar -Dlogback.configurationFile=$1 target/faker-0.1.jar $2 > ${OUT_LOG} 2>${ERROR_LOG} & echo $! > ${PID_PATH_NAME}
        echo "${SERVICE_NAME} started"
    else
        echo "${SERVICE_NAME} is already running"
    fi
}

stop() {
    if [ -f ${PID_PATH_NAME} ]; then
        PID=$(cat ${PID_PATH_NAME});
        echo "${SERVICE_NAME} stoping ..."
        kill ${PID};
        echo "${SERVICE_NAME} stopped"
        rm ${PID_PATH_NAME}
    else
        echo "${SERVICE_NAME} is not running ..."
    fi
}

case $1 in
    config)
        config
    ;;
    run)
        run $2 $3
    ;;
    start)
        start $2 $3
    ;;
    stop)
        stop
    ;;
    restart)
        stop
        start $2 $3
    ;;
esac