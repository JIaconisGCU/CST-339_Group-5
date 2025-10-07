package com.gcu.cst339_group5.game;

import java.time.LocalDate;

public record GameDTO(
        Long id,
        String title,
        String genre,
        String description,
        String award,
        String developer,
        String publisher,
        LocalDate releaseDate
) {
    public static GameDTO from(Game g) {
        return new GameDTO(
                g.getId(),
                g.getTitle(),
                g.getGenre(),
                g.getDescription(),
                g.getAward(),
                g.getDeveloper(),
                g.getPublisher(),
                g.getReleaseDate()
        );
    }
}
