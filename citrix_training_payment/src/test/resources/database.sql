CREATE DATABASE `payment_systems` /*!40100 COLLATE 'utf8_general_ci' */

CREATE TABLE `users` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(100) NULL DEFAULT NULL,
	`last_name` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

	
CREATE TABLE `transactions` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`transaction_id` BIGINT(20) NOT NULL,
	`status` TINYINT(4) NOT NULL,
	`date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`transaction_type` TINYINT(4) NOT NULL DEFAULT '0',
	`user_id` BIGINT(20) NULL DEFAULT '0',
	`amount` DECIMAL(20,4) NULL DEFAULT '0.0000',
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
