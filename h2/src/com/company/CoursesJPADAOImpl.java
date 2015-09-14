package com.company;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CoursesJPADAOImpl implements CoursesDAO {

    @Autowired
    private EntityManager entityManager;

    public void setEntityManager(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void addClient(Client c) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(c);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void addGroup(Group g) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(g);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public List<Client> getClients() {
        Query query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return (List<Client>) query.getResultList();
    }
}
