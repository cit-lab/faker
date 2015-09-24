#!/usr/bin/env bash

start() {
    echo "Start multiple faker"
    echo "Start default faker"
    faker.sh start etc/logback.xml etc/faker.xml
}

stop() {
   faker.sh stop etc/logback.xml etc/faker.xml
}

case $1 in
    start)
        start
    ;;
    stop)
        stop
    ;;
    restart)
        stop
        start
    ;;
esac