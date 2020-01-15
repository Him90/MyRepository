package com.trivadis.books.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;


import com.trivadis.books.client.BookDTO;
import com.trivadis.books.client.Genre;
import com.trivadis.books.server.Entity.BookEntity;
import com.trivadis.books.server.Entity.GenreEntity;
import com.trivadis.books.server.Repository.BookRepository;
import com.trivadis.books.server.Repository.GenreRepository;

@Component
@Transactional(value = TxType.REQUIRED)  //Spring data
public class BookManagementService {
	

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private GenreRepository genreRepository;

	public void addBook(BookDTO book) {

		BookEntity newBookEntity = new BookEntity();
		BeanUtils.copyProperties(book, newBookEntity);

		bookRepository.save(newBookEntity);		
	}
	
	public void addGenre(String genreTitle) {

		GenreEntity newGenreEntity = new GenreEntity();
		newGenreEntity.setGenreTitle(genreTitle);
		genreRepository.save(newGenreEntity);		
	}

	public List<BookDTO> getBooks() {
		List<BookDTO> bookDTOList = new ArrayList<BookDTO>();

		List<BookEntity> bookEntityList = bookRepository.findAll();


		for (BookEntity bookEntity : bookEntityList) {
			BookDTO book = new BookDTO();

			BeanUtils.copyProperties(bookEntity, book);

			
			bookDTOList.add(book);
			
		}

		return bookDTOList;
	}

	public List<Genre> getGenres() {
		List<Genre> genreList = new ArrayList<Genre>();

		List<GenreEntity> genreEntityList = genreRepository.findAll();


		for (GenreEntity genreEntity : genreEntityList) {
			Genre genre = new Genre();

			BeanUtils.copyProperties(genreEntity, genre);

			
			genreList.add(genre);
			
		}

		return genreList;
	}

		
	

}
