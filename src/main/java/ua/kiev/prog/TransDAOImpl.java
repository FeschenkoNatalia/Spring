package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by User on 12.09.2015.
 */
public class TransDAOImpl implements TransDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Transactions> list() {
        Query query = entityManager.createQuery("SELECT t FROM Transactions t", Transactions.class);
        return (List<Transactions>) query.getResultList();
    }

    @Override
    public List<Transactions> list(String pattern) {
        Query query = entityManager.createQuery("SELECT t FROM Transactions t WHERE t.transactionstypes.type LIKE :pattern", Transactions.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Transactions>) query.getResultList();
    }

    @Override
    public void add(Transactions trans) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(trans);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try {
            entityManager.getTransaction().begin();
            Transactions trans = entityManager.find(Transactions.class, id);
            entityManager.remove(trans);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
