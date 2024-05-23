package com.example.Compulsory.service;

import com.example.Compulsory.dao.BookDAO;
import com.example.Compulsory.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }
}
