package com.library.manager.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.manager.exceptions.BookNotFoundException;
import com.library.manager.models.Book;
import com.library.manager.repositories.BookRepository;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/books")
	public List<Book> listBooks() {
		return bookRepository.findAll();
	}

	@PostMapping("/books")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}

	@GetMapping("/books/{id}")
	public Book fetchBookById(@PathVariable(value = "id") Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}

	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable(value = "id") Long id, @Valid @RequestBody Book updateParams) {

		Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

		book.setTitle(updateParams.getTitle());
		book.setAuthor(updateParams.getAuthor());
		book.setGenre(updateParams.getGenre());
		book.setStatus(updateParams.getStatus());

		Book updatedBook = bookRepository.save(book);

		return updatedBook;
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) {

		Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

		bookRepository.delete(book);

		return ResponseEntity.ok().build();
	}

}
