CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(254) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(72) NOT NULL
);
    
CREATE TABLE IF NOT EXISTS games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50)  NOT NULL,
    year INT           NOT NULL,
    description TEXT,
    award VARCHAR(100),
    -- Optional: track who created the game
    created_by_user_id BIGINT,
    CONSTRAINT fk_games_user
        FOREIGN KEY (created_by_user_id) REFERENCES users(id)
);
