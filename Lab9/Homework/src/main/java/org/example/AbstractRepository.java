package org.example;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

public abstract class AbstractRepository<T, ID extends Serializable> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void create(T entity) {
        entityManager.persist(entity);
    }

    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }
}
