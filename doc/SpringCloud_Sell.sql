-- ��Ŀ
create table `product_category` (
    `category_id` int not null auto_increment,
    `category_name` varchar(64) not null comment '��Ŀ����',
    `category_type` int not null comment '��Ŀ���',
    `create_time` timestamp not null default current_timestamp comment '����ʱ��',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
    primary key (`category_id`),
    unique key `uqe_category_type` (`category_type`)
);
INSERT INTO `product_category` (`category_id`, `category_name`, `category_type`, `create_time`, `update_time`)
VALUES
	(1,'�Ȱ�',11,'2017-03-28 16:40:22','2017-11-26 23:39:36'),
	(2,'�óԵ�',22,'2017-03-14 17:38:46','2017-11-26 23:39:40');

-- ��Ʒ
create table `product_info` (
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '��Ʒ����',
    `product_price` decimal(8,2) not null comment '����',
    `product_stock` int not null comment '���',
    `product_description` varchar(64) comment '����',
    `product_icon` varchar(512) comment 'Сͼ',
    `product_status` tinyint(3) DEFAULT '0' COMMENT '��Ʒ״̬,0����1�¼�',
    `category_type` int not null comment '��Ŀ���',
    `create_time` timestamp not null default current_timestamp comment '����ʱ��',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
    primary key (`product_id`)
);
INSERT INTO `product_info` (`product_id`, `product_name`, `product_price`, `product_stock`, `product_description`, `product_icon`, `product_status`, `category_type`, `create_time`, `update_time`)
VALUES
	('157875196366160022','Ƥ����',0.01,39,'�óԵ�Ƥ����','//fuss10.elemecdn.com/0/49/65d10ef215d3c770ebb2b5ea962a7jpeg.jpeg',0,1,'2017-03-28 19:39:15','2017-07-02 11:45:44'),
	('157875227953464068','Ľ˹����',10.90,200,'��ζˬ��','//fuss10.elemecdn.com/9/93/91994e8456818dfe7b0bd95f10a50jpeg.jpeg',1,1,'2017-03-28 19:35:54','2017-04-21 10:05:57'),
	('164103465734242707','��֭����',0.02,982,'�ó�','//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg',0,1,'2017-03-30 17:11:56','2017-06-24 19:20:54');

-- ����
create table `order_master` (
    `order_id` varchar(32) not null,
    `buyer_name` varchar(32) not null comment '�������',
    `buyer_phone` varchar(32) not null comment '��ҵ绰',
    `buyer_address` varchar(128) not null comment '��ҵ�ַ',
    `buyer_openid` varchar(64) not null comment '���΢��openid',
    `order_amount` decimal(8,2) not null comment '�����ܽ��',
    `order_status` tinyint(3) not null default '0' comment '����״̬, Ĭ��Ϊ���µ�',
    `pay_status` tinyint(3) not null default '0' comment '֧��״̬, Ĭ��δ֧��',
    `create_time` timestamp not null default current_timestamp comment '����ʱ��',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
    primary key (`order_id`),
    key `idx_buyer_openid` (`buyer_openid`)
);

-- ������Ʒ
create table `order_detail` (
    `detail_id` varchar(32) not null,
    `order_id` varchar(32) not null,
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '��Ʒ����',
    `product_price` decimal(8,2) not null comment '��ǰ�۸�,��λ��',
    `product_quantity` int not null comment '����',
    `product_icon` varchar(512) comment 'Сͼ',
    `create_time` timestamp not null default current_timestamp comment '����ʱ��',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '�޸�ʱ��',
    primary key (`detail_id`),
    key `idx_order_id` (`order_id`)
);

-- �û�
CREATE TABLE `user_info` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) DEFAULT '',
  `password` varchar(32) DEFAULT '',
  `openid` varchar(64) DEFAULT '' COMMENT '΢��openid',
  `role` tinyint(1) NOT NULL COMMENT '1���2����',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��',
  PRIMARY KEY (`id`)
);