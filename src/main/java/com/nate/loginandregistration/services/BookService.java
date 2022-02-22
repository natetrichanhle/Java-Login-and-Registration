package com.nate.loginandregistration.services;

import java.util.List;
import java.util.Optional;

import com.nate.loginandregistration.models.Book;
import com.nate.loginandregistration.repositories.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	// adding the book repository as a dependency
	@Autowired
    private BookRepository bookRepository;

	// returns all the books
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}

	// creates a book
	public Book createBook(Book b) {
		return bookRepository.save(b);
	}

	// retrieves a book
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}

	public Book updateBook(Book updatedBook) {
			return bookRepository.save(updatedBook);
	}

	public void deleteBook(Long id) {
			bookRepository.deleteById(id);			
	}
}
