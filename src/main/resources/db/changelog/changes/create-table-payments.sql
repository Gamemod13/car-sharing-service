--liquibase formatted sql
--changeset igor_maks:create-table-users splitStatements:true endDelimeter: ;

CREATE TABLE IF NOT EXISTS `payments`  (
                         `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `status` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `type` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
						`rental_id` BIGINT unsigned NOT NULL,
                         `session_url` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `session_id` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `suma` DECIMAL(5, 2) UNSIGNED NOT NULL,
                         `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;