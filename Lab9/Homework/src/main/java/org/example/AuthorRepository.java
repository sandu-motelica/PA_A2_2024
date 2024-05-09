package org.example;

import org.example.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AuthorRepository extends AbstractRepository<Author, Long> {

    private static final Logger logger = Logger.getLogger(AuthorRepository.class.getName());


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

    public AuthorRepository(EntityManagerFactory emf) {
        super(Author.class);
        this.entityManager = emf.createEntityManager();
    }

    @Transactional
    public void create(Author author) {
        entityManager.persist(author);
    }

    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    public Author findByName(String name) {
        long startTime = System.currentTimeMillis();
        Author author = entityManager.createQuery(
                        "SELECT a FROM Author a WHERE a.name = :name", Author.class)
                .setParameter("name", name)
                .getSingleResult();
        long executionTime = System.currentTimeMillis() - startTime;
        logger.info("JPQL(Find author by name) query executed in " + executionTime + " ms");
        return author;
    }

}
