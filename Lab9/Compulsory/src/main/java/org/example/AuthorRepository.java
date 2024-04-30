package org.example;

import org.example.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void create(Author author) {
        entityManager.persist(author);
    }

    public Author findById(Long id) {
        return entityManager.find(Author.class, id);
    }

    public List<Author> findByName(String name) {
        return entityManager.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", name)
                .getResultList();
    }
}
