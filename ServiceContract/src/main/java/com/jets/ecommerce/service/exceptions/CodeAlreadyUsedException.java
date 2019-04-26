package com.jets.ecommerce.service.exceptions;


public class CodeAlreadyUsedException extends Exception {
	
	public CodeAlreadyUsedException(String message) {
        super(message);
    }

    public CodeAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeAlreadyUsedException(Throwable cause) {
        super(cause);
    }

}
