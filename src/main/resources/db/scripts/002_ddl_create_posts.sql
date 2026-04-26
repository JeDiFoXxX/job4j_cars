--liquibase formatted sql
--changeset Jedifox:v.1
CREATE TABLE IF NOT EXISTS posts (
    id BIGSERIAL PRIMARY KEY,
    auto_user_id BIGINT NOT NULL REFERENCES auto_user(id) ON DELETE CASCADE,
    car_id BIGINT NOT NULL REFERENCES cars(id) ON DELETE CASCADE,
    description VARCHAR(1000) NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);