-- User Management Service Schema
CREATE TABLE users (
   user_id UUID PRIMARY KEY,
   username VARCHAR(255) UNIQUE NOT NULL,
   password_hash VARCHAR(255) NOT NULL,
   email VARCHAR(255),
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
   role_id UUID PRIMARY KEY,
   role_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
    group_id UUID NOT NULL ,
    user_id UUID NOT NULL,
    role_id UUID REFERENCES roles(role_id) ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (group_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE NOT NULL
);