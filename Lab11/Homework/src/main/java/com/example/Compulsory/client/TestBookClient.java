package com.example.Compulsory.client;

import com.example.Compulsory.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestBookClient implements CommandLineRunner {

    @Autowired
    private BookClient bookClient;

    @Override
    public void run(String... args) throws Exception {
        Book newBook = new Book();
        newBook.setTitle("Noua Carte");
        newBook.setLanguage("Română");
        newBook.setPublicationDate(new Date());
        Book addedBook = bookClient.addBook(newBook);
        System.out.println("Cartea adăugată: " + addedBook.getTitle());

        Book[] books = bookClient.getAllBooks();
        System.out.println("Toate cărțile: ");
        for(Book b: books){
            System.out.println(b);
        }

        if (books.length > 0) {
            Book book = bookClient.getBookById(books[0].getId());
            System.out.println("Cartea găsită: " + book.getTitle());

            bookClient.updateBookTitle(book.getId(), "Titlul Actualizat");
            Book updatedBook = bookClient.getBookById(book.getId());
            System.out.println("Cartea actualizată: " + updatedBook);

            bookClient.deleteBook(book.getId());
            System.out.println("Cartea cu ID " + book.getId() + " a fost ștearsă.");
        }
    }
}
