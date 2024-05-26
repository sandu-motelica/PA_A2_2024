package com.example.Compulsory.dao;

import com.example.Compulsory.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Integer> {
}

