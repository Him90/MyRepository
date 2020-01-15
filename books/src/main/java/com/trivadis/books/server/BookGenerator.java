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
			service.addBook(new BookDTO());
			service.addBook(new BookDTO());
			service.addBook(new BookDTO());

		}

	}

	
