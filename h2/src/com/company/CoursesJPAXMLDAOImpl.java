package com.company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CoursesJPAXMLDAOImpl implements CoursesDAO {

    private EntityManager entityManager;
    private String entityName;
    private boolean inited;

    public void setEntityName(String name) {
        entityName = name;
    }

    private void init() {
        if ( ! inited) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(entityName);
            entityManager = emf.createEntityManager();
            inited = true;
        }
    }

    @Override
    public void addClient(Client c) {
        init();
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
        init();
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
        init();
        Query query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return (List<Client>) query.getResultList();
    }
}
