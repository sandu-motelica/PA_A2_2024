package org.example;

import org.example.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Author author = new Author();
        author.setName("Mark Twain");
        em.persist(author);
        em.getTransaction().commit();

        Author foundAuthor = em.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", "Mark Twain")
                .getSingleResult();

        System.out.println("Found author: " + foundAuthor.getName());

        em.close();
        emf.close();
    }
}