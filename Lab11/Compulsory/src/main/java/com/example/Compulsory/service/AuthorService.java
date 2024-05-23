package com.example.Compulsory.service;

import com.example.Compulsory.dao.AuthorDAO;
import com.example.Compulsory.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    public List<Author> getAllAuthors() {
        return authorDAO.findAll();
    }
}
