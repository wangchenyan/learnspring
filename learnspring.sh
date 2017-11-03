#!/bin/bash
#
#一键部署脚本

PROCESS_NAME=learnspring.jar
PROJECT_PATH=/home/wcy/project/learnspring
JAR_PATH=${PROJECT_PATH}/app/target/learnspring.jar
APP_PATH=/home/wcy/app/learnspring
DEPLOY_PATH=${APP_PATH}/learnspring.jar
OUTPUT_PATH=${APP_PATH}/learnspring.out

#Stop
PID=`ps -ef|grep ${PROCESS_NAME}|grep -v grep|grep -v PPID|awk '{ print $2}'`
if [ ${PID} ];then
    echo '> Kill Process!'
    kill -9 ${PID}
fi

#Package
cd ${PROJECT_PATH}

echo '> Update Code...'
git pull

echo '> Clean...'
mvn clean

echo '> Package...'
mvn package

if [ -f ${JAR_PATH} ];then
    echo '> Package Success!'
    cp -rf ${JAR_PATH} ${DEPLOY_PATH}
    echo '> Copy Success!'

    #Start
    cd ${APP_PATH}
    nohup java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar ${DEPLOY_PATH} > ${OUTPUT_PATH} 2>&1 &
    echo '> Start Success!'
else
    echo '> Package Failed!'
fi