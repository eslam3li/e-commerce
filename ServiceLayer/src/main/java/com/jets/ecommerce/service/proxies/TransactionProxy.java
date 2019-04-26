
package com.jets.ecommerce.service.proxies;

import com.ibrahim.hibernate.transaction.TransactionManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class TransactionProxy implements InvocationHandler {

    private final Object service;
    private final TransactionManager transactionManager;

    private TransactionProxy(Object service, TransactionManager transactionManager) {
        this.service = service;
        this.transactionManager = transactionManager;
    }

    public static <T> T wrap(T service, TransactionManager transactionManager) {
        return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new TransactionProxy(service, transactionManager));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return transactionManager.runInTransaction(() -> {
            try {
                return method.invoke(service, args);
            } catch (InvocationTargetException e) {
                throw (Exception) e.getCause();
            }
        });
    }

}
