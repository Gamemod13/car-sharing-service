CREATE TABLE IF NOT EXISTS `payments`  (
                         `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `status` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `type` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
						 `rental_id` BIGINT unsigned NOT NULL,
                         `session_url` VARCHAR(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `session_id` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `amount_to_pay` DECIMAL(19, 2) UNSIGNED NOT NULL,
                         PRIMARY KEY (`id`) USING BTREE,
    INDEX `FK_rental_id`(`rental_id`) USING BTREE,
    CONSTRAINT `FK_rental_id` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;