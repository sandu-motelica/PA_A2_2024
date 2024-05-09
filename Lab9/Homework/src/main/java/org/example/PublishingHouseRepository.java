package org.example;

import org.example.entities.PublishingHouse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class PublishingHouseRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void create(PublishingHouse publishingHouse) {
        entityManager.persist(publishingHouse);
    }

    public PublishingHouse findById(Long id) {
        return entityManager.find(PublishingHouse.class, id);
    }

}