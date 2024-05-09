package org.example;

import org.example.entities.Author;
import org.example.entities.Book;
import org.example.entities.BookAuthor;
import org.example.entities.PublishingHouse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
        EntityManager em = emf.createEntityManager();

//        Author author = new Author();
//        author.setName("Mircea Eliade");
        AuthorRepository authorRepository = new AuthorRepository(emf);
        Author author = authorRepository.findByName("Mircea Eliade");

//        em.getTransaction().begin();
//        em.persist(author);
//        em.getTransaction().commit();

        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("Cartex");

        em.getTransaction().begin();
        em.persist(publishingHouse);
        em.getTransaction().commit();

        Book book = new Book();
        book.setTitle("Sacrul și profanul");
        book.setPublishingHouse(publishingHouse);
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();

        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setBook(book);
        bookAuthor.setAuthor(author);

        em.getTransaction().begin();
        em.persist(bookAuthor);
        em.getTransaction().commit();

        BookRepository bookRepository = new BookRepository(emf);
        List<Book> booksByAuthor = bookRepository.findByAuthor(author);
        System.out.println("Books by author " + author.getName() + ":");
        for (Book b : booksByAuthor) {
            System.out.println("- " + b.getTitle());
        }

        boolean isAssociated = bookRepository.isBookAssociatedWithAuthor(book.getId(), author.getId());
        System.out.println("Is book associated with author? " + (isAssociated ? "Yes" : "No"));

        em.close();
        emf.close();
    }
}
