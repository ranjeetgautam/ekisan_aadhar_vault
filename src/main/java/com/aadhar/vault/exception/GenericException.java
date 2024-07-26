package com.aadhar.vault.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final String message;
    public HttpStatus code;

    public GenericException(String message) {
        super(message);
        this.message = message;
    }

    public GenericException(String message, HttpStatus code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public GenericException(String message, Throwable t) {
        super(message, t);
        this.message = message;
        this.code = null;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }
}


