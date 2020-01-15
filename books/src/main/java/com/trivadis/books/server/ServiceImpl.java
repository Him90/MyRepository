package com.trivadis.books.server;

import com.trivadis.books.client.BookDTO;
import com.trivadis.books.client.Service;
import com.trivadis.books.shared.FieldVerifier;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {
	
	

	@Override
	public void addBook(BookDTO book) {
		BookManagementService.addBook(book);
		
	}
	
	@Override
	public List<BookDTO> getBooks() {
		return BookManagementService.getBooks();
		
	}
}
