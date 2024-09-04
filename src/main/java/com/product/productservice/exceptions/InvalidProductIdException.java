package com.product.productservice.exceptions;

public class InvalidProductIdException extends Exception {
    public InvalidProductIdException(){

    }
    public InvalidProductIdException(String messege){
        super(messege);
    }
}
