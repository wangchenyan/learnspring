#!/bin/bash
#
#一键部署脚本

APP_NAME=learnspring
WAR_PATH=/home/wcy/project/learnspring/app/target/learnspring.jar
DEPLOY_PATH=/home/wcy/app/learnspring/learnspring.jar
OUTPUT_PATH=/home/wcy/app/learnspring/learnspring.out


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
cd /home/wcy/project/learnspring/

echo '> Update Code...'
git pull

echo '> Clean...'
mvn clean

echo '> Package...'
mvn package

if [ -f $WAR_PATH ];
then
    echo '> Package Success!'
    cp -rf $WAR_PATH $DEPLOY_PATH
    echo '> Copy Success!'


    #Start
    nohup java -jar $DEPLOY_PATH > $OUTPUT_PATH 2>&1 &
    echo 'Start Success!'
else
    echo '> Package Failed!'
fi