package com.jonlandrum.api.account;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class AccountService {
    @PersistenceContext//(name = "API_DEMO", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    public void createAccount(Account account) { }

    /**
     * Retrieve the {@link Account} with the specified ID
     * @param id {@link Long} value representing a {@link Account} ID
     * @return {@link Account} with the specified ID, or null if not found
     */
    public Account getAccount(final Long id) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT a FROM Account a WHERE a.id = ?1", Account.class);
        query.setParameter(1, id);
        List<Account> result = query.getResultList();
        if( result.isEmpty() ) {
            return null;
        } else if (result.size() == 1) {
            return result.get(0);
        } else {
            throw new NonUniqueResultException();
        }
    }

    /**
     * Persist changes to a {@link Account}
     * @param account the {@link Account} to persist
     */
    public void save(final Account account) {
//        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
//            session.beginTransaction();
//            session.save(account);
//            session.getTransaction().commit();
//        }
    }
}
