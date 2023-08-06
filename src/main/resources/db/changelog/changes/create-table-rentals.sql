CREATE TABLE IF NOT EXISTS `rentals`  (
                         `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
						 `rental_date` DATETIME NOT NULL,
						 `return_date` DATETIME NOT NULL,
						 `actual_return_date` DATETIME NULL,
						 `car_id` BIGINT unsigned NOT NULL,
						 `user_id` BIGINT unsigned NOT NULL,
                         PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_car_id`(`car_id`) USING BTREE,
    INDEX `FK_user_id`(`user_id`) USING BTREE,
    CONSTRAINT `FK_car_id` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`),
    CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;