package com.trivadis.books.server.Repository;


import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trivadis.books.server.Entity.GenreEntity;

@Repository
@Transactional(value = TxType.MANDATORY)
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
	
	GenreEntity findOneByGenreTitleIgnoringCaseContains(String genreTitle);


}

