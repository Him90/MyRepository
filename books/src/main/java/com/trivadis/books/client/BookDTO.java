package com.trivadis.books.client;

import java.io.Serializable;

public class BookDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2689189179759713643L;
	

	private Long id;
	private String bookTitle;
	private Genre bookGenre;
	
	public BookDTO() {

	}
	
	public BookDTO(String bookTitle, Genre bookGenre) {
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
	
	public String getBookGenreTitle() {
		return this.getBookGenre().getGenreTitle();
	}
	public void setBookGenreTitle(String genreTitle) {
		this.getBookGenre().setGenreTitle(genreTitle);;
	}
	
	
}
