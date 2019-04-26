package com.ibrahim.hibernate.transaction;

public interface UnitOfWork<T> {

    /**
     * Implement your work here and it will run within a transactional context
     * by calling <code>{@link TransactionManager#runInTransaction}</code>. You
     * should obtain hibernate session by calling
     * {@link org.hibernate.SessionFactory#getCurrentSession}
     *
     * @return result of this unit of work
     * @throws Exception
     */
    T execute() throws Exception;

}
