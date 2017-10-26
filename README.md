# learnspring
Spring学习记录

## 配置云主机
CentOS 6.5

### 登录云主机
打开Xshell，点击新建，根据要求输入相应参数
```
名称：自定义设置
协议：SSH
主机：主机公网IP
端口号：22
```
选择用户身份认证
```
方法选择：Password
用户名：默认用户名为root
密码：在主机中自定义的密码
```

### 安装JDK
yum安装
```
# yum list java*
# yum install -y java-1.8.0-openjdk*
```

### 安装lrzsz
```
# yum install -y lrzsz
```

### 安装MySQL
yum安装
```
# yum install -y mysql-server
```
设置开机自启
```
# chkconfig mysqld on
```
启动MySql服务
```
# service mysqld start
```
设置root用户密码
```
// 登录root
# mysql -u root
// 查询用户的密码，都为空
mysql> select user,host,password from mysql.user;
// 设置root的密码为yourpassword
mysql> set password for root@localhost=password('yourpassword');
mysql> exit
```
用新密码登录
```
# mysql -u root -p
```
开放远程登录权限
```
mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'yourpassword' WITH GRANT OPTION;
mysql> FLUSH PRIVILEGES;
```

**设置MySQL编码**

查看编码
```
mysql> SHOW VARIABLES LIKE 'character%';
```

设置编码
```
退出MySQL
mysql> exit

编辑/etc/my.cnf，添加

[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
character-set-server=utf8

重启MySQL
# service mysqld restart

重新连接MySQL
# mysql -u root -p
```

### 安装Redis
下载并解压
```
# cd /home/wcy/software/
# wget http://download.redis.io/releases/redis-4.0.1.tar.gz
# tar -zxf redis-4.0.1.tar.gz
```
编译及安装
```
# cd redis-4.0.1
# make && make install
```
复制配置文件
```
# cp redis.conf /etc/
```
修改配置文件
```
# vi /etc/redis.conf

找到`daemonize`改为`yes`设置后台运行
找到`requirepass`设置密码
```
设置开机自启
```
复制启动文件
# cp utils/redis_init_script /etc/init.d/redis

修改配置
# vi /etc/init.d/redis
在#!/bin/sh下添加
# add by wangchenyan
# chkconfig: 2345 90 10
# description: Redis is a persistent key-value database
修改CONF文件位置
CONF="/etc/redis.conf"

保存退出

启动服务
# service redis start

设置开机自启
# chkconfig redis on

测试
# redis-cli
```

解决`redis.clients.jedis.HostAndPort - cant resolve localhost address`
```
查看Linux系统的主机名
# hostname
jdu4e00u53f7.jcloud.local
查看/etc/hosts文件中是否有127.0.0.1对应主机名，如果没有则添加
# vi /etc/hosts
127.0.0.1   localhost ... jdu4e00u53f7.jcloud.local
```

### 安装Git
yum安装
```
# yum install -y git
```

### 安装Maven
下载并解压
```
# cd /home/wcy/software/
# wget http://mirror.bit.edu.cn/apache/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz
# tar -zxf apache-maven-3.5.0-bin.tar.gz
```
添加环境变量
```
# vi /etc/profile
添加
export MAVEN_HOME=/home/wcy/software/apache-maven-3.5.0
export PATH=$PATH:$MAVEN_HOME/bin
```
刷新环境变量
```
# source /etc/profile
```
查看是否添加成功
```
# mvn -version
```

### 添加一键部署脚本
clone项目
```
# cd /home/wcy/project/
# git clone https://github.com/wangchenyan/learnspring.git
```
复制打包脚本
```
# cp /home/wcy/project/learnspring/deploy.sh /usr/local/bin/
```
添加可执行权限
```
# chmod +x /usr/local/bin/deploy.sh
```
一键部署
```
# deploy.sh
```