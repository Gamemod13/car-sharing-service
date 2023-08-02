--liquibase formatted sql
--changeset igor_maks:alter-table-cars-inventory splitStatements:true endDelimiter: ;

ALTER TABLE `cars`
    MODIFY COLUMN `inventory` INT(0) UNSIGNED NOT NULL;