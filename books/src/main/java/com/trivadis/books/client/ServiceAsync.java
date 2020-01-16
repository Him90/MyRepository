package com.trivadis.books.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceAsync {

	void addBook(BookDTO book, AsyncCallback<Void> asyncCallback);

	void getBooks(AsyncCallback<List<BookDTO>> asyncCallback);

	void getGenres(AsyncCallback<List<Genre>> asyncCallback);

	void deleteBook(BookDTO book, AsyncCallback<Void> asyncCallback);

}
