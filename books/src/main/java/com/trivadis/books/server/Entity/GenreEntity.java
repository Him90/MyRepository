package com.trivadis.books.server.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GenreEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	private String genreTitle;

	public GenreEntity() {
		this.genreTitle = "none";
	}

	public GenreEntity(String genreTitle) {
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
