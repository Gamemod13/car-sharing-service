--liquibase formatted sql
--changeset igor_maks:create-table-cars splitStatements:true endDelimeter: ;
CREATE TABLE IF NOT EXISTS `cars`  (
                         `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `model` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `brand` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `type` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `inventory` BIGINT(0) UNSIGNED NOT NULL,
                         `daily_fee` DECIMAL(5, 2) UNSIGNED NOT NULL,
                         `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;