--liquibase formatted sql
--changeset Jedifox:v.1
CREATE TABLE IF NOT EXISTS engine (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);