#!/bin/bash
#
#一键部署脚本

APP_NAME=learnspring
PROJECT_PATH=/home/wcy/project/learnspring
JAR_PATH=$PROJECT_PATH/app/target/learnspring.jar
APP_PATH=/home/wcy/app
DEPLOY_PATH=$APP_PATH/learnspring.jar
OUTPUT_PATH=$APP_PATH/learnspring.out


#Stop
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ];
then
    echo '> Stop Process...'
    kill -15 $tpid
fi
sleep 5
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ];
then
    echo '> Kill Process!'
    kill -9 $tpid
else
    echo '> Stop Success!'
fi


#Package
cd $PROJECT_PATH

echo '> Update Code...'
git pull

echo '> Clean...'
mvn clean

echo '> Package...'
mvn package

if [ -f $JAR_PATH ];
then
    echo '> Package Success!'
    cp -rf $JAR_PATH $DEPLOY_PATH
    echo '> Copy Success!'


    #Start
    cd $APP_PATH
    nohup java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar $DEPLOY_PATH > $OUTPUT_PATH 2>&1 &
    echo '> Start Success!'
else
    echo '> Package Failed!'
fi