package com.example.Compulsory.dao;

import com.example.Compulsory.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {
}

