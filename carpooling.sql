# Host: 127.0.0.1  (Version: 5.5.20)
# Date: 2015-10-20 10:05:12
# Generator: MySQL-Front 5.3  (Build 4.13)

/*!40101 SET NAMES utf8 */;

#
# Source for table "temp_orders"
#

DROP TABLE IF EXISTS `temp_orders`;
CREATE TABLE `temp_orders` (
  `orderNum` int(11) NOT NULL,
  `carType` char(10) DEFAULT NULL,
  `seatTotal` int(3) DEFAULT NULL,
  `seatAvailable` int(3) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `starting` varchar(255) DEFAULT NULL,
  `ending` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "temp_orders"
#

INSERT INTO `temp_orders` VALUES (13,'BMW',11,9,'2016-01-01','11:11:00','WJL','nanjin','BLT;TT;TDD'),(17,'WMB',11,11,'2016-01-01','11:11:00','tianda','nanjin','hehe;');

#
# Source for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL DEFAULT '',
  `age` varchar(3) DEFAULT NULL,
  `carOwner` int(1) DEFAULT NULL,
  `drivingYears` varchar(255) DEFAULT NULL,
  `gender` char(10) DEFAULT NULL,
  `cellphone` char(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES ('jingshihao','DaDiao','jinshan','20',1,'19','male','15510952431');

#
# Source for table "user_orders"
#

DROP TABLE IF EXISTS `user_orders`;
CREATE TABLE `user_orders` (
  `id` char(10) NOT NULL DEFAULT '0',
  `orderNum` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`orderNum`),
  KEY `orderNum` (`orderNum`),
  CONSTRAINT `user_orders_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_orders_ibfk_2` FOREIGN KEY (`orderNum`) REFERENCES `temp_orders` (`orderNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_orders"
#

INSERT INTO `user_orders` VALUES ('jingshihao',13);
