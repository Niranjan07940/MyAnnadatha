--liquibase formatted sql

--changeset pavan:5

INSERT INTO categories(category_name)
VALUES
    ('Vegetables'),
    ('Fruits'),
    ('Grains');