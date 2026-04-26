--liquibase formatted sql
--changeset Jedifox:v.1
CREATE TABLE IF NOT EXISTS photos (
    id BIGSERIAL PRIMARY KEY,
    post_id BIGINT NOT NULL REFERENCES posts(id) ON DELETE CASCADE,
    url VARCHAR(255) NOT NULL
);