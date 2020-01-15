package com.trivadis.books.server.Repository;


import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trivadis.books.server.Entity.BookEntity;


//Für Spring Data


@Repository
@Transactional(value = TxType.MANDATORY)
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	

}


