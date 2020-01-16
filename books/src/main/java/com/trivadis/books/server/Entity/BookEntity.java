package com.trivadis.books.server.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;

import com.trivadis.books.client.Genre;

@Entity
public class BookEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, columnDefinition = "VARCHAR(80)")
	private String bookTitle;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_id")
	private GenreEntity genreEntity;// = new GenreEntity();
	
	public BookEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public BookEntity(String bookTitle, Genre bookGenre) {
		this.bookTitle = bookTitle;
		//this.bookGenre = bookGenre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String booktTitle) {
		this.bookTitle = booktTitle;
	}
	public GenreEntity getGenreEntity() {
		return genreEntity;
	}
	public void setGenreEntity(GenreEntity genreEntity) {
		this.genreEntity = genreEntity;
	}
	

	

}
