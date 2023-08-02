--liquibase formatted sql
--changeset igor_maks:alter-table-rentals-actual splitStatements:true endDelimiter: ;

ALTER TABLE `rentals`
    MODIFY COLUMN `actual_return_date` DATETIME NULL;
