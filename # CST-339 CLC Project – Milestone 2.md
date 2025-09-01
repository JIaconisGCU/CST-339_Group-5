# CST-339 CLC Project – Milestone 2  
**Role 1: Presentation / UX Lead**

---

## Cover Page – Tasks Completed by Role 1
- Created global Thymeleaf layout (`common.html`) with Bootstrap integration.  
- Implemented responsive navbar with links to Home, Login, Register.  
- Built `home.html` page extending the layout.  
- Integrated teammates’ pages (`login.html`, `register.html`) into layout for consistent look and feel.  
- Corrected Thymeleaf namespace issues to ensure layout processing.  
- Ensured responsiveness works on desktop, tablet, and mobile (tested with Chrome DevTools).  
- Verified UI consistency after resolving merge conflicts.  

---

## Planning Documentation (Role 1 perspective)
- **Role split**: Carlos handled Presentation/UX (common layout, Home page, navbar, responsive testing). Teammates handled Registration and Login controllers + forms.  
- **Workflow**:  
  - Each teammate developed in local branch.  
  - All code merged to `development` branch on GitHub.  
  - Merge conflicts were resolved, prioritizing:  
    - Role 1 for layout/UI code.  
    - Role 2 & 3 for Registration/Login controllers.  
- **Peer review**: teammates reviewed layout changes and tested in browsers before merging.  

---

## General Technical Approach (Update from Milestone 1)
- Used **Spring Boot 3.5.5** with **Thymeleaf** for dynamic views.  
- Added **Thymeleaf Layout Dialect** to support reusable layout (`common.html`).  
- Used **Bootstrap 5** (via CDN) for responsive design.  
- Ensured all pages (`home`, `login`, `register`) extend `common.html` for consistency.  

---

## Key Technical Design Decisions
- Chose Thymeleaf Layout Dialect instead of copy/paste headers/footers → promotes reuse and maintainability.  
- Implemented one **global navbar** with links to Home, Login, Register (to be adapted for authentication in later milestones).  
- Chose Bootstrap’s grid system for responsiveness instead of custom CSS.  
- Deleted `LoginRegisterController.java` to avoid duplicate mappings and errors, ensuring controllers map uniquely.  

---

## Install / Configuration Instructions (for layout work)
1. Ensure the following dependency exists in `pom.xml`:  

   ```xml
   <dependency>
       <groupId>nz.net.ultraq.thymeleaf</groupId>
       <artifactId>thymeleaf-layout-dialect</artifactId>
   </dependency>

---
---

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


