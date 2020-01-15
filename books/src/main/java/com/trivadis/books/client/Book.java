package com.trivadis.books.client;

import java.io.Serializable;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2689189179759713643L;
	

	private Long id;
	private String booktTitle;
	private String bookGenre;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBooktTitle() {
		return booktTitle;
	}
	public void setBooktTitle(String booktTitle) {
		this.booktTitle = booktTitle;
	}
	public String getBookGenre() {
		return bookGenre;
	}
	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}
	
	
}
