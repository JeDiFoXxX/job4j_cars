--liquibase formatted sql
--changeset Jedifox:v.1
CREATE TABLE IF NOT EXISTS cars (
    id BIGSERIAL PRIMARY KEY,
    engine_id BIGINT NOT NULL REFERENCES engine(id) ON DELETE RESTRICT,
    owner_id BIGINT NOT NULL REFERENCES owners(id) ON DELETE RESTRICT,
    name VARCHAR(255) NOT NUll
);