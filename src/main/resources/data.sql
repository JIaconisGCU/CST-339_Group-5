INSERT INTO users (first_name, last_name, email, phone, username, password)
SELECT 'Test', 'User', 'test@example.com', '123-456-7890', 'testuser', 'password123'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'testuser');
-- === SAMPLE GAMES ===
INSERT INTO games (title, genre, year, description, award)
VALUES
('The Witcher 3: Wild Hunt', 'RPG', 2015, 'Story-driven open-world RPG', 'Game of the Year 2015'),
('Halo 3', 'FPS', 2007, 'Sci-fi first-person shooter', 'Best Multiplayer'),
('The Legend of Zelda: Breath of the Wild', 'Action/Adventure', 2017, 'Open-world adventure', 'Game of the Year 2017');