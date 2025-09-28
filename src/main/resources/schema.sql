CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(72) NOT NULL
    role VARCHAR(30) NOT NULL DEFAULT 'ROLE_USER',
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);
    
CREATE TABLE IF NOT EXISTS games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    description TEXT,
    award VARCHAR(100),
    developer VARCHAR(100) NOT NULL,
    publisher VARCHAR(100) NOT NULL,
    release_date DATE NOT NULL,
    created_by_user_id BIGINT,
    CONSTRAINT fk_games_user
        FOREIGN KEY (created_by_user_id) REFERENCES users(id)
);
