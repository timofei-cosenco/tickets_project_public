CREATE TABLE `requests` (
                            `request_id` bigint NOT NULL,
                            `blocks_id_block` bigint DEFAULT NULL,
                            `first_name` varchar(45) NOT NULL,
                            `last_name` varchar(45) NOT NULL,
                            `email` varchar(45) NOT NULL,
                            `phone` varchar(45) NOT NULL,
                            `cabinet_number` varchar(5) NOT NULL,
                            `category` varchar(15) NOT NULL,
                            `body_request` varchar(255) NOT NULL,
                            `employee_comment` varchar(255),
                            `dispatch_time` varchar(25) NOT NULL DEFAULT 'PENDING',
                            `lead_time` varchar(25) NOT NULL,
                            `employee_id` bigint DEFAULT NULL,
                            `status` varchar(10) NOT NULL DEFAULT 'ACTIVE',
                            PRIMARY KEY (`request_id`),
                            KEY `FKjwqdsf8xp2tqqktd1rbkamuwd` (`blocks_id_block`),
                            CONSTRAINT `FKjwqdsf8xp2tqqktd1rbkamuwd` FOREIGN KEY (`blocks_id_block`) REFERENCES `blocks` (`block_id`)
)