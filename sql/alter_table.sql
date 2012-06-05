-- added by wangfei at 2012-06-04
create database guitarme;
use guitarme;
create table jitapu(
  id int(11) not null auto_increment primary key,
  data text not null comment '原始数据'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '从jitapu获取的吉他谱';
-- added by wangfei at 2012-06-04