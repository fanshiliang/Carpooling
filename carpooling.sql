# Host: 127.0.0.1  (Version: 5.5.20)
# Date: 2015-12-15 22:18:22
# Generator: MySQL-Front 5.3  (Build 4.13)

/*!40101 SET NAMES utf8 */;

#
# Source for table "orders"
#

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderNum` int(11) NOT NULL,
  `orderType` char(10) DEFAULT NULL,
  `carType` char(10) DEFAULT NULL,
  `seatTotal` int(3) DEFAULT NULL,
  `seatAvailable` int(3) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `starting` varchar(255) DEFAULT NULL,
  `ending` varchar(255) DEFAULT NULL,
  `route` varchar(255) DEFAULT NULL,
  `status` char(10) DEFAULT NULL,
  PRIMARY KEY (`orderNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "orders"
#

INSERT INTO `orders` VALUES (2,'Temp','BMW',10,10,'2015-12-21','2015-12-30','12:30:00','北洋园','老校区','TianTa,','ongoing'),(3,'Long','QQ',20,20,'2015-12-01','2015-12-30','08:00:00','老校区','北洋园','','ongoing'),(4,'Temp','OOOO',5,5,'2015-12-30','2015-12-30','07:30:00','老校区','新校区','','ongoing');

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
  `id` varchar(255) NOT NULL DEFAULT '0',
  `orderNum` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`orderNum`),
  KEY `orderNum` (`orderNum`),
  CONSTRAINT `user_orders_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_orders_ibfk_2` FOREIGN KEY (`orderNum`) REFERENCES `orders` (`orderNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_orders"
#

INSERT INTO `user_orders` VALUES ('jingshihao',4);
