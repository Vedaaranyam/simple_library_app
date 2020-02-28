package com.library.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.manager.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
