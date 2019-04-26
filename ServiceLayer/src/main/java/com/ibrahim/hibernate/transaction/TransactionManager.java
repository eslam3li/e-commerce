package com.ibrahim.hibernate.transaction;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

final public class TransactionManager {

    SessionFactory sessionFactory;

    public TransactionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * This method will run unit of work within hibernate transaction context
     *
     * @param <T>
     * @param unitOfWork the work to be executed within transaction context
     * @return the result of unit of work
     * @throws Exception as thrown by unit of work
     */
    public <T> T runInTransaction(UnitOfWork<T> unitOfWork) throws Exception {
        Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
        try {
            return unitOfWork.execute();
        } catch (Exception ex) {
            if (tx.getStatus().canRollback()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (tx.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                tx.commit();
            }
        }
    }

}
