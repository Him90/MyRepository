package com.trivadis.books.server;


	import javax.annotation.PostConstruct;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;

import com.trivadis.books.client.BookDTO;


	@Component
	public class BookGenerator {
		
		@Autowired
		private BookManagementService service;
			
		@PostConstruct
		private void generateData() {
//			BookDTO newBook = new BookDTO();
//			newBook.setBookTitle("The Dreaming Void");
//			newBook.getBookGenre().setGenreTitle("SciFi");
//			service.addBook(newBook);
//			newBook.setBookTitle("The name of the wind");
//			newBook.getBookGenre().setGenreTitle("Fantasy");
//			service.addBook(newBook);
//			newBook.setBookTitle("The long walk");
//			newBook.getBookGenre().setGenreTitle("Novel");
//			service.addBook(newBook);

		}

	}

	
