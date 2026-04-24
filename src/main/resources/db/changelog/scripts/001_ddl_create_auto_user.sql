CREATE TABLE IF NOT EXISTS auto_user (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    CONSTRAINT unique_login UNIQUE (login)
);