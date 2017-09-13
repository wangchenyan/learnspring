CREATE DATABASE wcy;

CREATE TABLE user(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL UNIQUE COMMENT '用户名',
    db_create_time timestamp NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
    db_update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    password VARCHAR(64) NOT NULL COMMENT '密码',
    phone_number VARCHAR(64) COMMENT '手机号码',
    nickname VARCHAR(64) COMMENT '昵称',
    signature VARCHAR(256) COMMENT '个性签名'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
