-- added by wangfei at 2012-06-04
create database guitarme;
use guitarme;
create table jitapu(
  id int(11) not null auto_increment primary key,
  data text not null comment '原始数据'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '从jitapu获取的吉他谱';
-- added by wangfei at 2012-06-04

create table tab(
`id` int(11) not null auto_increment primary key,
`name` varchar(64) not null comment '曲名',
`singer` varchar(64) not null comment '歌手/乐队',
`source` varchar(64) not null comment '编排、来源',
`content` text not null comment '曲谱内容',
`site` varchar(64) not null DEFAULT ''
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '吉他谱初步结构化存储';
-- added by wangfei @ 2012-09-06


create table tab_txt(
`id` int(11) not null auto_increment primary key,
`name` varchar(64) not null comment '曲名',
`singer_id` int(11) not null comment '歌手id',
`singer` varchar(64) not null comment '歌手',
`content` text not null comment '曲谱内容',
`comment` text not null comment '备注',
`key_origin` varchar(32) not null comment '原调',
`key_chosen` varchar(32) not null comment '选调',
`add_time` datetime not null comment '添加时间',
`last_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '文本谱';
-- added by wangfei @2013-01-08

create table chord_fret(
`id` int(11) not null auto_increment primary key,
`name` varchar(64) not null comment '和弦名称',
`chord_id` int(11) not null comment '和弦id',
`root_note` varchar(4) not null comment '根音',
`position` int(11) not null comment '把位',
`fret_numbers` varchar(64) not null comment '指板数值序列 从6弦到1弦',
`finger` varchar(64) not null comment '指法序列 从6弦到1弦',
`comment` varchar(128) comment '备注'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '和弦按法';
-- added by wangfei@2013-02-21

CREATE TABLE `audit_log` (
  `id` varchar(36) NOT NULL,
  `access_log_id` varchar(255) NOT NULL COMMENT '访问日志id，可能为可能为空',
  `entity_name` varchar(255) NOT NULL COMMENT 'domain类型',
  `entity_id` varchar(255) NOT NULL COMMENT 'domain 主键',
  `field_name`varchar(255) NOT NULL COMMENT '字段名称',
  `old_value` varchar(2048)  NOT NULL COMMENT '字段旧值',
  `new_value` varchar(2048) NOT NULL COMMENT '字段新值',
  `modifier_id` integer NOT NULL COMMENT '操作者id',
  `create_time` datetime  NOT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='业务审计日志';



create table social_connection (
    id INT(11) NOT NULL AUTO_INCREMENT,
    userId varchar(255) not null,
    providerId varchar(255) not null,
    providerUserId varchar(255),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(255) not null,
    secret varchar(255),
    refreshToken varchar(255),
    expireTime bigint,
    create_time datetime not null,
    PRIMARY KEY (id),
    unique key (userId, providerId, providerUserId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='spring social connections';
create unique index user_connect_rank on social_connection(userId, providerId, rank);
--added by wangfei@2013-04-24

create table account(
    id varchar(64) NOT NULL primary key COMMENT 'userId',
    `name` varchar(64) NOT NULL COMMENT '用户名'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='gm 用户';
