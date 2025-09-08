package com.gcu.cst339_group5.game;

import java.time.LocalDate;

public class Game {
	private String title;
	private String developer;
	private String publisher;
	private LocalDate releaseDate;
	
	
	
	public String getTitle() {
		return title;
	}
	public String getDeveloper() {
		return developer;
	}
	public String getPublisher() {
		return publisher;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
}
