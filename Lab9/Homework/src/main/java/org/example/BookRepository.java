package org.example;

import org.example.entities.Author;
import org.example.entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;
import java.util.logging.*;

public class BookRepository extends AbstractRepository<Book, Long> {
    private static final Logger logger = Logger.getLogger(BookRepository.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("application.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to setup logger handler.", e);
        }
    }


    private EntityManager entityManager;

    public BookRepository(EntityManagerFactory emf) {
        super(Book.class);
        this.entityManager = emf.createEntityManager();
    }

    @Override
    public void create(Book entity) {
        super.create(entity);
    }

    @Override
    public Book findById(Long id) {
        return super.findById(id);
    }

    public List<Book> findByAuthor(Author author) {
        long startTime = System.currentTimeMillis();
        List<Book> books = entityManager.createQuery(
                        "SELECT b FROM Book b JOIN b.bookAuthors ba ON b.id = ba.book.id WHERE ba.author.id = :authorId", Book.class)
                .setParameter("authorId", author.getId())
                .getResultList();
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("JPQL (Find books by author) query executed in " + executionTime + " ms");
        System.out.println(books.size());
        return books;
    }

    public boolean isBookAssociatedWithAuthor(Long bookId, Long authorId) {
        long startTime = System.currentTimeMillis();
        Long count = entityManager.createQuery(
                        "SELECT COUNT(ba) FROM BookAuthor ba WHERE ba.book.id = :bookId AND ba.author.id = :authorId", Long.class)
                .setParameter("bookId", bookId)
                .setParameter("authorId", authorId)
                .getSingleResult();
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("JPQL (Is book associate with author) query executed in " + executionTime + " ms");

        return count > 0;
    }

}
