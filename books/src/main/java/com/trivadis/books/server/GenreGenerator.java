package com.trivadis.books.server;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreGenerator {

	@Autowired
	private BookManagementService service;

	@PostConstruct
	private void generateData() {
		service.addGenre("SciFi");
		service.addGenre("Fantasy");
		service.addGenre("Horror");

	}

}
