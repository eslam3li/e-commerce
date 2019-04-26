/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.exceptions;


public class UnauthorizedActionExeption extends RuntimeException {

    public UnauthorizedActionExeption() {
    }

    public UnauthorizedActionExeption(String message) {
        super(message);
    }

    public UnauthorizedActionExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedActionExeption(Throwable cause) {
        super(cause);
    }

}
