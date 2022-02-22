package com.nate.loginandregistration.repositories;

import java.util.List;

import com.nate.loginandregistration.models.Book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	// this method retrieves all the books from the database
	List<Book> findAll();
}
