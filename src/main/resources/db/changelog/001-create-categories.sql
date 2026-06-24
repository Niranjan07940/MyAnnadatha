--liquibase formatted sql

--changeset pavan:1

CREATE TABLE categories
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);