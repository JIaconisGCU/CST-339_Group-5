INSERT INTO users (first_name, last_name, email, phone, username, password)
SELECT 'Test', 'User', 'test@example.com', '123-456-7890', 'testuser', 'password123'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'testuser');
-- === SAMPLE GAMES ===
INSERT INTO games (title, genre, description, award, developer, publisher, release_date)
VALUES
('The Witcher 3: Wild Hunt', 'RPG', 'Story-driven open-world RPG', 'Game of the Year 2015', 'CD Projekt Red', 'CD Projekt', '2015-05-19'),
('Halo 3', 'FPS', 'Sci-fi first-person shooter', 'Best Multiplayer', 'Bungie', 'Microsoft', '2007-09-25'),
('The Legend of Zelda: Breath of the Wild', 'Action/Adventure', 'Open-world adventure', 'Game of the Year 2017', 'Nintendo EPD', 'Nintendo', '2017-03-03');