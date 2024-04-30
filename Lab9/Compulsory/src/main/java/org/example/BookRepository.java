//package org.example;
//
//import org.example.entities.Book;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import java.util.List;
//
//public class BookRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Transactional
//    public void create(Book book) {
//        entityManager.persist(book);
//    }
//
//    public Book findById(Long id) {
//        return entityManager.find(Book.class, id);
//    }
//
//    public List<Book> findAll() {
//        return entityManager.createNamedQuery("Book.findAll", Book.class)
//                .getResultList();
//    }
//
//    public List<Book> findByTitle(String title) {
//        return entityManager.createNamedQuery("Book.findByTitle", Book.class)
//                .setParameter("title", title)
//                .getResultList();
//    }
//}
//
