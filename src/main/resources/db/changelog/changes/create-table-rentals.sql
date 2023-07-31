--liquibase formatted sql
--changeset igor_maks:create-table-users splitStatements:true endDelimeter: ;

CREATE TABLE IF NOT EXISTS `rentals`  (
                         `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
						 `rental_date` DATETIME NOT NULL,
						 `return_date` DATETIME NOT NULL,
						 `actual_return_date` DATETIME NOT NULL,
						`car_id` BIGINT unsigned NOT NULL,
						`user_id` BIGINT unsigned NOT NULL,
                         `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;