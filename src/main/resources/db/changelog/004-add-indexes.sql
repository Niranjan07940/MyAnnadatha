--liquibase formatted sql

--changeset pavan:4

CREATE INDEX idx_subcategory_category
    ON sub_category(categories_id);

CREATE INDEX idx_subcategory_unit
    ON sub_category(unit_id);