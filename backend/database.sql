create database SSAFYDB;

use SSAFYDB;

CREATE TABLE `board` (
  `board_id` bigint NOT NULL AUTO_INCREMENT,
  `user_no` bigint DEFAULT NULL,
  `board_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` char(20) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `feed` (
  `feed_id` bigint NOT NULL AUTO_INCREMENT,
  `user_no` bigint DEFAULT NULL,
  `feed_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`feed_id`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `feed_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `news` (
  `news_id` bigint NOT NULL AUTO_INCREMENT,
  `news_title` varchar(50) DEFAULT NULL,
  `news_link` varchar(200) DEFAULT NULL,
  `news_date` timestamp NULL DEFAULT NULL,
  `news_description` varchar(200) DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  `news_img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`news_id`),
  KEY `board_id` (`board_id`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  CONSTRAINT `news_ibfk_2` FOREIGN KEY (`user_no`) REFERENCES `board` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT,
  `user_no` bigint DEFAULT NULL,
  `post_dir_id` bigint DEFAULT NULL,
  `post_title` varchar(100) DEFAULT NULL,
  `post_content` varchar(10000) DEFAULT NULL,
  `post_date` timestamp NULL DEFAULT NULL,
  `post_favorite` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `user_no` (`user_no`),
  KEY `post_dir_id` (`post_dir_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`post_dir_id`) REFERENCES `post_dir` (`post_dir_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `post_dir` (
  `post_dir_id` bigint NOT NULL AUTO_INCREMENT,
  `user_no` bigint DEFAULT NULL,
  `post_dir_name` char(20) DEFAULT NULL,
  PRIMARY KEY (`post_dir_id`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `post_dir_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `post_tag` (
  `post_tag_id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint DEFAULT NULL,
  `tag_name` char(20) DEFAULT NULL,
  PRIMARY KEY (`post_tag_id`),
  KEY `post_id` (`post_id`),
  KEY `tag_name` (`tag_name`),
  CONSTRAINT `post_tag_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `post_tag_ibfk_2` FOREIGN KEY (`tag_name`) REFERENCES `tag` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

CREATE TABLE `rss` (
  `rss_id` bigint NOT NULL AUTO_INCREMENT,
  `rss_url` varchar(500) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `rss_name` char(40) DEFAULT NULL,
  PRIMARY KEY (`rss_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `rss_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

CREATE TABLE `subscribe` (
  `subscribe_id` bigint NOT NULL AUTO_INCREMENT,
  `subscribe_name` char(30) DEFAULT NULL,
  `feed_id` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  `rss_id` bigint DEFAULT NULL,
  PRIMARY KEY (`subscribe_id`),
  KEY `user_no` (`user_no`),
  KEY `feed_id` (`feed_id`),
  KEY `rss_id` (`rss_id`),
  CONSTRAINT `subscribe_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
  CONSTRAINT `subscribe_ibfk_2` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`),
  CONSTRAINT `subscribe_ibfk_3` FOREIGN KEY (`rss_id`) REFERENCES `rss` (`rss_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

CREATE TABLE `tag` (
  `tag_name` char(20) NOT NULL,
  PRIMARY KEY (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `user_no` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `user_password` char(80) NOT NULL,
  `user_name` char(20) NOT NULL,
  `user_type` int DEFAULT NULL,
  PRIMARY KEY (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
