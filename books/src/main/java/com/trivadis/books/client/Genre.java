package com.trivadis.books.client;

import java.io.Serializable;

public class Genre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1757486252146679819L;
	

	private Long id;
	private String genreTitle;
	
	public Genre() {
		this.genreTitle = "none";

	}
	
	public Genre(String genreTitle) {
		this.genreTitle = genreTitle;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGenreTitle() {
		return genreTitle;
	}
	public void setGenreTitle(String genreTitle) {
		this.genreTitle = genreTitle;
	}
	

	public void setGenre(String genreTitle) {
		this.genreTitle = genreTitle;
	}


}
