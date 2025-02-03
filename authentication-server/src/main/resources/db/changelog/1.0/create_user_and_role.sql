--liquibase formatted sql

-- changeset user:1
CREATE TABLE IF NOT EXISTS "users" (
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(255),
                        login VARCHAR(255),
                        password VARCHAR(255)
);

-- changeset user:2
CREATE TABLE IF NOT EXISTS user_roles (
                            user_id BIGINT NOT NULL,
                            role VARCHAR(50) NOT NULL,
                            CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES "users" (id)
);

-- changeset user:3
ALTER TABLE users
ADD COLUMN created_date TIMESTAMP WITH TIME ZONE;
ALTER TABLE users
ADD COLUMN last_update_date TIMESTAMP WITH TIME ZONE;

