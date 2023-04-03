CREATE TABLE `employee_block` (
                                  `employee_id` bigint DEFAULT NULL,
                                  `block_id` bigint NOT NULL,
                                  KEY `FKgcd2lv733bfecnpuo79j615cy` (`block_id`),
                                  KEY `FKb9s4xfskct0k1hbiqbwmy5w6y` (`employee_id`),
                                  CONSTRAINT `FKb9s4xfskct0k1hbiqbwmy5w6y` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON DELETE SET NULL ON UPDATE SET NULL,
                                  CONSTRAINT `FKgcd2lv733bfecnpuo79j615cy` FOREIGN KEY (`block_id`) REFERENCES `blocks` (`block_id`) ON DELETE CASCADE ON UPDATE CASCADE
)