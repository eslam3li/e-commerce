/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.security;


public class SecurityContext {

    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    public static void setUser(User user) {
        CURRENT_USER.set(user);
    }

    public static void clear() {
        CURRENT_USER.remove();
    }

    public static User getUser() {
        return CURRENT_USER.get();
    }

    public static boolean isVisitor() {
        return CURRENT_USER.get() == null;
    }

    public static boolean isNotVisitor() {
        return !isVisitor();
    }

    public static boolean isClient() {
        return isNotVisitor() && CURRENT_USER.get() instanceof Client;
    }

    public static boolean isAdmin() {
        return isNotVisitor() && CURRENT_USER.get() instanceof Admin;
    }

    public static int getCurrentUserId() {
        if (isVisitor()) {
            throw new UnkownUserException("The user identity is unkown");
        }
        return CURRENT_USER.get().getId();
    }

}
