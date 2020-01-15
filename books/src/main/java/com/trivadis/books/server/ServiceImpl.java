package com.trivadis.books.server;

import com.trivadis.books.client.BookDTO;
import com.trivadis.books.client.Genre;
import com.trivadis.books.client.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {
	
	@Autowired
	private BookManagementService service;	

	@Override
	public void addBook(BookDTO book) {
		service.addBook(book);
		
	}
	
	@Override
	public List<BookDTO> getBooks() {
		return service.getBooks();
		
	}

	@Override
	public List<Genre> getGenres() {
		return service.getGenres();
	}
}
