#!/bin/bash
#
#一键部署脚本

PROJECT_PATH=/home/wcy/project/learnspring
JAR_PATH_APP=${PROJECT_PATH}/app/target/learnspring.jar
JAR_PATH_XIAOCAO=${PROJECT_PATH}/xiaocao/target/xiaocao.jar
JAR_PATH_PRINTER=${PROJECT_PATH}/printer/target/printer.jar
DEPLOY_PATH=/home/wcy/app/learnspring
DEPLOY_PATH_APP=${DEPLOY_PATH}/learnspring.jar
DEPLOY_PATH_XIAOCAO=${DEPLOY_PATH}/xiaocao.jar
DEPLOY_PATH_PRINTER=${DEPLOY_PATH}/printer.jar
OUTPUT_PATH_APP=${DEPLOY_PATH}/learnspring.out
OUTPUT_PATH_XIAOCAO=${DEPLOY_PATH}/xiaocao.out
OUTPUT_PATH_PRINTER=${DEPLOY_PATH}/printer.out

# $1 PROCESS_NAME
killProcess(){
    PID=`ps -ef|grep $1|grep -v grep|grep -v PPID|awk '{print $2}'`
    if [ ${PID} ];then
        echo "> Kill Process $1!"
        kill -9 ${PID}
    fi
}

# $1 JAR_PATH $2 DEPLOY_PATH $3 OUTPUT_PATH $4 DEBUG_PORT $5 NAME
deploy(){
    if [ -f $1 ];then
        echo "> Package Success $5!"
        cp -rf $1 $2
        echo "> Copy Success $5!"

        #Start
        cd ${DEPLOY_PATH}
        nohup java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$4 -jar $2 >$3 2>&1 &
        echo "> Start Success $5!"
    else
        echo "> Package Failed $5!"
    fi
}

#Stop
killProcess learnspring.jar
killProcess xiaocao.jar
killProcess printer.jar

#Package
cd ${PROJECT_PATH}

echo '> Update Code...'
git pull

echo '> Clean...'
mvn clean

echo '> Install...'
mvn install

echo '> Package...'
mvn package

deploy ${JAR_PATH_PRINTER} ${DEPLOY_PATH_PRINTER} ${OUTPUT_PATH_PRINTER} 5007 'Printer'
sleep 3s
deploy ${JAR_PATH_XIAOCAO} ${DEPLOY_PATH_XIAOCAO} ${OUTPUT_PATH_XIAOCAO} 5006 'Xiaocao'
sleep 3s
deploy ${JAR_PATH_APP} ${DEPLOY_PATH_APP} ${OUTPUT_PATH_APP} 5005 'App'
sleep 3s