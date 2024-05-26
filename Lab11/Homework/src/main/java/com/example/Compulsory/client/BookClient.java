package com.example.Compulsory.client;

import com.example.Compulsory.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081/api/books";

    public Book[] getAllBooks() {
        ResponseEntity<Book[]> response = restTemplate.getForEntity(BASE_URL, Book[].class);
        return response.getBody();
    }

    public Book getBookById(int id) {
        String url = BASE_URL + "/" + id;
        ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);
        return response.getBody();
    }

    public Book addBook(Book book) {
        ResponseEntity<Book> response = restTemplate.postForEntity(BASE_URL, book, Book.class);
        return response.getBody();
    }

    public void updateBookTitle(int id, String newTitle) {
        String url = BASE_URL + "/" + id + "/title?newTitle=" + newTitle;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
    }

    public void deleteBook(int id) {
        String url = BASE_URL + "/" + id;
        restTemplate.delete(url);
    }
}
