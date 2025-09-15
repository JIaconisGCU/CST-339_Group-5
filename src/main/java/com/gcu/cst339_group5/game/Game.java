package com.gcu.cst339_group5.game;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;

@Table("games")
public class Game {

    @Id
    private Long id; // null on create -> INSERT

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @NotBlank(message = "Developer is required")
    @Size(max = 100, message = "Developer must be at most 100 characters")
    private String developer;

    @NotBlank(message = "Publisher is required")
    @Size(max = 100, message = "Publisher must be at most 100 characters")
    private String publisher;

    @PastOrPresent(message = "Release date cannot be in the future")
    @Column("release_date") // maps camelCase -> snake_case
    private LocalDate releaseDate;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
}
