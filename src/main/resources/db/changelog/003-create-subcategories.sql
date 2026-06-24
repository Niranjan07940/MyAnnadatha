--liquibase formatted sql

--changeset pavan:3

CREATE TABLE sub_category
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    item_name VARCHAR(100) NOT NULL,

    quantity INT,

    categories_id BIGINT NOT NULL,

    unit_id BIGINT NOT NULL,

    CONSTRAINT fk_subcategory_category
        FOREIGN KEY(categories_id)
            REFERENCES categories(id),

    CONSTRAINT fk_subcategory_unit
        FOREIGN KEY(unit_id)
            REFERENCES units(id)
);