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