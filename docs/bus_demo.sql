-- ----------------------------
-- Table structure for bus_demo
-- ----------------------------
CREATE TABLE `bus_demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of bus_demo
-- ----------------------------
INSERT INTO `bus_demo` VALUES ('1', 'this is data');
