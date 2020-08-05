create database SSAFYDB;

use SSAFYDB;

create table `USER`(
	`user_no` bigint auto_increment,
	`user_id` varchar(50) NOT NULL,
    `user_password` char(80) NOT NULL,
    `user_name` char(20) NOT NULL,
    `user_type` int,
	PRIMARY KEY (`user_no`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table `CATEGORY`(
	`category_id` bigint auto_increment,
    `category_name` char(20),
    PRIMARY KEY (`category_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `RSS`(
	`rss_id` bigint auto_increment,
    `rss_url` varchar(500),
    `category_id` bigint,
    PRIMARY KEY (`rss_id`),
    FOREIGN KEY (`category_id`) REFERENCES CATEGORY (`category_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `FEED`(
	`feed_id` bigint auto_increment,
	`user_no` bigint,
    `feed_name` varchar(50),
    PRIMARY KEY (`feed_id`),
    FOREIGN KEY (`user_no`) REFERENCES USER (`user_no`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `SUBSCRIBE`(
	`subscribe_id` bigint auto_increment,
    `subscribe_name` char(30),
	`feed_id` bigint,
	`user_no` bigint,
    `rss_id` bigint,
    PRIMARY KEY (`subscribe_id`),
    FOREIGN KEY (`user_no`) REFERENCES USER (`user_no`),
    FOREIGN KEY (`feed_id`) REFERENCES FEED (`feed_id`),
    FOREIGN KEY (`rss_id`) REFERENCES RSS (`rss_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `POST_DIR`(
	`post_dir_id` bigint auto_increment,
	`user_no` bigint,
    `post_dir_name` char(20),
    PRIMARY KEY (`post_dir_id`),
    FOREIGN KEY (`user_no`) REFERENCES USER (`user_no`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `POST`(
	`post_id` bigint auto_increment,
	`user_no` bigint,
    `post_dir_id` bigint,
    `post_title` varchar(100),
    `post_content` varchar(10000),
    `post_date` timestamp,
    `post_favorite` boolean,
    PRIMARY KEY (`post_id`),
    FOREIGN KEY (`user_no`) REFERENCES USER (`user_no`),
    FOREIGN KEY (`post_dir_id`) REFERENCES POST_DIR (`post_dir_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `TAG`(
	`tag_id` bigint auto_increment,
	`tag_name` char(20),
    PRIMARY KEY (`tag_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `POST_TAG`(
	`post_tag_id` bigint auto_increment,
	`post_id` bigint,
	`tag_id` bigint,
    PRIMARY KEY (`post_tag_id`),
    FOREIGN KEY (`post_id`) REFERENCES POST (`post_id`),
    FOREIGN KEY (`tag_id`) REFERENCES TAG (`tag_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `BOARD` (
	`board_id` bigint auto_increment,
	`user_no` bigint,
    `board_name` varchar(50),
    PRIMARY KEY (`board_id`),
    FOREIGN KEY (`user_no`) REFERENCES USER (`user_no`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `news` (
	`news_id` bigint auto_increment,
    `news_title` varchar(50),
    `new_link` varchar(200),
    `new_date` timestamp,
    `new_description` varchar(200),
    `board_id` bigint,
    PRIMARY KEY (`news_id`),
    FOREIGN KEY (`board_id`) REFERENCES BOARD (`board_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
