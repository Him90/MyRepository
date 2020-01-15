package com.trivadis.books.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("bookStore")
public interface Service extends RemoteService {

	void addBook(BookDTO book);

	List<BookDTO> getBooks();

	List<Genre> getGenres();
}
