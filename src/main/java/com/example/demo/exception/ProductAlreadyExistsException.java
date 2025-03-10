package com.example.demo.exception;

public class ProductAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public ProductAlreadyExistsException(Throwable ex){
        super(ex);
    }
    
    public ProductAlreadyExistsException(String message){
        super(message);
    }

    public ProductAlreadyExistsException(String message, Throwable ex){
        super(message, ex);
    }

}
