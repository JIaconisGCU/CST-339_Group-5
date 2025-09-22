INSERT INTO users (first_name, last_name, email, phone, username, password)
SELECT 'Test', 'User', 'test@example.com', '123-456-7890', 'testuser', 'password123'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'testuser');
-- === SAMPLE GAMES ===
INSERT INTO games (title, genre, description, award, developer, publisher, release_date)
SELECT 'The Witcher 3: Wild Hunt','RPG','Story-driven open-world RPG','Game of the Year 2015','CD Projekt Red','CD Projekt','2015-05-19'
WHERE NOT EXISTS (
  SELECT 1 FROM games WHERE title='The Witcher 3: Wild Hunt' AND release_date='2015-05-19'
);

INSERT INTO games (title, genre, description, award, developer, publisher, release_date)
SELECT 'Halo 3','FPS','Sci-fi first-person shooter','Best Multiplayer','Bungie','Microsoft','2007-09-25'
WHERE NOT EXISTS (
  SELECT 1 FROM games WHERE title='Halo 3' AND release_date='2007-09-25'
);

INSERT INTO games (title, genre, description, award, developer, publisher, release_date)
SELECT 'The Legend of Zelda: Breath of the Wild','Action/Adventure','Open-world adventure','Game of the Year 2017','Nintendo EPD','Nintendo','2017-03-03'
WHERE NOT EXISTS (
  SELECT 1 FROM games WHERE title='The Legend of Zelda: Breath of the Wild' AND release_date='2017-03-03'
);
