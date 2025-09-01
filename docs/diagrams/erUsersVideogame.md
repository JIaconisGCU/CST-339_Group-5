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
