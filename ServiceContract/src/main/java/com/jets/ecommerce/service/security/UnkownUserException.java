/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jets.ecommerce.service.security;


public class UnkownUserException extends RuntimeException {

    public UnkownUserException(String message) {
        super(message);
    }

}
