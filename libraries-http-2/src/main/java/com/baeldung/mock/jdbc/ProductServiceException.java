package com.baeldung.mock.jdbc;

public class ProductServiceException extends Exception {
    public ProductServiceException(String message){
        super(message);
    }
}
