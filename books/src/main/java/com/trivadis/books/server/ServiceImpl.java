package com.trivadis.books.server;

import com.trivadis.books.client.BookDTO;
import com.trivadis.books.client.Genre;
import com.trivadis.books.client.Service;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {

	@Autowired
	private BookManagementService service;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContextUtils.findWebApplicationContext(config.getServletContext()).getAutowireCapableBeanFactory()
				.autowireBean(this);
	}

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

	@Override
	public void deleteBook(BookDTO book) {
		service.deleteBook(book);

	}
}
