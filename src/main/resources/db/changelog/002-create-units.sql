--liquibase formatted sql

--changeset pavan:2

CREATE TABLE units
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    unit VARCHAR(20) NOT NULL
);