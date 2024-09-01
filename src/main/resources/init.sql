-- User Management Service Schema
CREATE TABLE users (
    user_id              UUID PRIMARY KEY,
    username             VARCHAR(255) UNIQUE NOT NULL,
    password_hash        VARCHAR(255) NOT NULL,
    email                VARCHAR(255) NOT NULL,
    profile_picture_url  VARCHAR(255),
    banner_picture_url   VARCHAR(255),
    created_at           TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);