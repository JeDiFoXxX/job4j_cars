CREATE TABLE IF NOT EXISTS history_owners (
    car_id BIGINT NOT NULL REFERENCES cars(id) ON DELETE CASCADE,
    owner_id BIGINT NOT NULL REFERENCES owners(id) ON DELETE CASCADE,
    PRIMARY KEY (car_id, owner_id)
);