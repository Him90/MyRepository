package com.trivadis.books.server.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.trivadis.books.client.Genre;

@Entity
public class BookEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String bookTitle;
	private Genre bookGenre;
	
	public BookEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public BookEntity(String bookTitle, Genre bookGenre) {
		this.bookTitle = bookTitle;
		this.bookGenre = bookGenre;
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
	public Genre getBookGenre() {
		return bookGenre;
	}
	public void setBookGenre(String bookGenre) {
		this.bookGenre.setGenreTitle(bookGenre);
	}
	

	

}
