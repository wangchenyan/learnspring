#!/bin/bash
#
#自动部署脚本

warPath="/home/wcy/projects/learnspring/app/target/app-1.0-SNAPSHOT.war"
destPath="/home/wcy/apache-tomcat-7.0.81/webapps/app.war"

cd /home/wcy/projects/learnspring/

printf "正在更新代码..."
git pull

printf "正在打包..."
mvn clean package

if [-x "warPath"]; then
　　cp -rf "warPath" "destDir"
else
    printf "打包失败..."
fi