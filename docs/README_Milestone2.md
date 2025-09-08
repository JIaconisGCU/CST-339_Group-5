# CST-339 CLC Project – Milestone 2  
**James Pinto, Role 2: Registration / User Management Lead**

---

## Cover Page – Tasks Completed by Role 2
- Created `User` model class with validation annotations (`@NotBlank`, `@Email`, `@Pattern`, `@Size`).  
- Built `RegisterController` with GET (form) and POST (process) mappings.  
- Developed `register.html` Thymeleaf form integrated with Bootstrap, including error messages.  
- Added `register-success.html` confirmation page (later redirected to `/login` for session-based login flow).  
- Implemented `InMemoryUserStore` component to temporarily store registered users until database integration.  
- Coordinated with LoginController (Role 3) to allow authentication against registered users.  
- Added draft database design for `users` table, including ER diagram, class diagram, and DDL script.  

---

## Planning Documentation (Role 2 perspective)
- **Role split**: James handled Registration/User Management, Carlos handled Presentation/UX, Justin handled Login/session management.  
- **Workflow**:  
  - Registration logic was developed in a separate feature branch (`james/registration`).  
  - Integrated with `development` branch after validation with team.  
  - Collaborated with Role 1 to ensure `register.html` used the new global layout (`common.html`).  
  - Coordinated with Role 3 so that login could use registered users from the in-memory store.  

---

## General Technical Approach (Update from Milestone 1)
- Implemented `User` model with server-side validation (email format, phone number pattern, password length).  
- Used **Thymeleaf forms** for binding and error display.  
- Added `InMemoryUserStore` as a placeholder repository for users, allowing registration/login workflow before database setup.  
- Drafted SQL DDL script for `users` table and supporting diagrams (class diagram and ER snippet).  
- Future milestones: move from in-memory store → MySQL via Spring Data JDBC with hashed passwords (BCrypt).  

---

## Key Technical Design Decisions
- Used Bean Validation (Jakarta Validation) for consistent server-side validation rules.  
- Chose an in-memory store for simplicity and to unblock login integration before DB setup.  
- Redirected successful registration to `/login` so session is managed consistently in one place.  
- Drafted DDL script with unique constraints (`username`, `email`) to enforce data integrity.  
- Created ER and class diagrams for documentation; placed them in `/docs/` folder for maintainability.  

---

## Install / Configuration Instructions (for registration work)
1. Ensure the following dependency exists in `pom.xml`:  
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-validation</artifactId>
   </dependency>

---
## ER Diagram
  ```mermaid
  erDiagram
      USER ||--o{ VIDEOGAME : "created_by (future)"
      USER {
          BIGINT id PK
          VARCHAR first_name
          VARCHAR last_name
          VARCHAR email
          VARCHAR phone
          VARCHAR username
          VARCHAR password_hash
          DATETIME created_at
          DATETIME updated_at
      }
      VIDEOGAME {
          BIGINT id PK
          VARCHAR title
          VARCHAR genre
          INT year
          VARCHAR description
          BIGINT created_by FK
      }
```
#### *Figure 1* ER diagram snippet showing the users table and its future relationship to videogame entities.
---
## User Class Diagram
  ```mermaid
  classDiagram
class User {
  - firstName : String
  - lastName  : String
  - email     : String
  - phone     : String
  - username  : String
  - password  : String
  + get/set...
}
```
#### *Figure 2* UML Class Diagram of the User model showing attributes and validation rules for registration. 
---
## DDL draft - *users* table
```sql
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
```
#### *Figure 3* Draft SQL DDL script for the *users* table with uniqueness contraints and timestamps.

---
---

# CST-339 CLC Project – Milestone 2  
**Justin Iaconis, Role 3: Login Function, Repository Manager**

---

## Cover Page – Tasks Completed by Role 3
- Created project GitHub repository, with the "live" `master` branch and the `development` branch.
- Built HTML page to provide login functionality with a submission form
- Built `LoginController` class that provides mapping for the login page
- `LoginController` can set and clear the user in the HTTP session.
- Ensured the navbar updated appropriately based on if the user was logged in.
- Resolved merging conflicts in the repositry.
- Ensured the project was functional on every team members' machines after merging the `development` branch to the `master` branch.

---

## Planning Documentation (Role 3 perspective)
- **Role split**: James handled Registration and User Management, Carlos handled the webpages, layout, and fragments, and Justin handled login functionality and managed the repository.  
- **Workflow**:  
  - Developed login logic in a separate feature branch (`justin/feature/login`).  
  - Branch was pushed and merged with `development` branch after verifying readiness with other team members.  
  - Managed repository as other team members submitted their features into the `development` branch.
  - Coordinated with team members to fix any issues, such as conflicts while pushing to the repository.
  - Worked with Role 1 to ensure the navbar fragment was responding correctly to the user's login status.
  - Pulled the `development` branch's commits into the `master` branch.

---

## General Technical Approach (Update from Milestone 1)
- Used **Thymeleaf forms** for binding and error display.
- Used GitHub to maintain the project repository.

---

## Key Technical Design Decisions
- Tied login information to HTTP session's attributes
- If the user is not logged in, the session's `username` attribute will be null or empty, otherwise it will be their username.
- All attributes are cleared upon logout.

---

## Future Risks & Concerns
- Temporary hard-coded username and password verification must be replaced with a verification system integrated with the user registration and database system.
