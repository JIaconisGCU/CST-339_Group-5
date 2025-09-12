INSERT INTO users (first_name, last_name, email, phone, username, password)
SELECT 'Test', 'User', 'test@example.com', '123-456-7890', 'testuser', 'password123'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'testuser');