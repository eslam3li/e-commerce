/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.exceptions;


public class RegisterationException extends Exception {

    public RegisterationException(String message) {
        super(message);
    }

    public RegisterationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterationException(Throwable cause) {
        super(cause);
    }

}
