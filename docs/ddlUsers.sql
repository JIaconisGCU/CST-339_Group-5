-- Draft DDL: users (prototype; no persistence wired in this milestone)
CREATE TABLE users (
  id             BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name     VARCHAR(50)  NOT NULL,
  last_name      VARCHAR(50)  NOT NULL,
  email          VARCHAR(254) NOT NULL UNIQUE,
  phone          VARCHAR(20)  NOT NULL,
  username       VARCHAR(30)  NOT NULL UNIQUE,
  password_hash  VARCHAR(255) NOT NULL,
  created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
