package com.trivadis.books.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
