--liquibase formatted sql
CREATE TABLE IF NOT EXISTS `cars`  (
                         `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `model` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `brand` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `type` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `inventory` INTEGER UNSIGNED NOT NULL,
                         `daily_fee` DECIMAL(5, 2) UNSIGNED NOT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;