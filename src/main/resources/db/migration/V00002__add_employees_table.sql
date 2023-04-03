CREATE TABLE `employees` (
                             `employee_id` bigint NOT NULL,
                             `first_name` varchar(255) NOT NULL,
                             `last_name` varchar(255) NOT NULL,
                             `login` varchar(45) NOT NULL,
                             `password` varchar(255) NOT NULL,
                             `category` varchar(15) NOT NULL,
                             `role` varchar(20) NOT NULL DEFAULT 'USER',
                             `status` varchar(20) DEFAULT 'ACTIVE',
                             PRIMARY KEY (`employee_id`),
                             UNIQUE KEY `login_UNIQUE` (`login`)
)