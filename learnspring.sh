#!/bin/bash
#
#一键部署脚本

APP_NAME=learnspring
PROJECT_PATH=/home/wcy/project/learnspring
JAR_PATH=${PROJECT_PATH}/app/target/learnspring.jar
APP_PATH=/home/wcy/app/learnspring
DEPLOY_PATH=${APP_PATH}/learnspring.jar
OUTPUT_PATH=${APP_PATH}/learnspring.out


#Stop
PROCESS=`ps -ef|grep ${APP_NAME}|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in ${PROCESS}
do
    echo "> Kill the ${APP_NAME} process [ ${i} ]"
    kill -9 ${i}
done


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
    rm -f tpid
    nohup java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar ${DEPLOY_PATH} > ${OUTPUT_PATH} 2>&1 &
    echo $! > tpid
    echo '> Start Success!'
else
    echo '> Package Failed!'
fi