
package com.jets.ecommerce.service.proxies;

import com.jets.ecommerce.service.exceptions.UnauthorizedActionExeption;
import com.jets.ecommerce.service.security.SecurityContext;
import com.jets.ecommerce.service.security.annotations.Access;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class AccessProxy implements InvocationHandler {

    private final Object service;

    private AccessProxy(Object service) {
        this.service = service;
    }

    public static <T> T wrap(T service) {
        return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new AccessProxy(service));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Access annotation = method.getAnnotation(Access.class);
        if (annotation != null
                && !annotation.value().isInstance(SecurityContext.getUser())) {
            throw new UnauthorizedActionExeption();
        }
        try {
            return method.invoke(service, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

}
