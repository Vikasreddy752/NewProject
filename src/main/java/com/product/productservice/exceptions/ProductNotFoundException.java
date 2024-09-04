package com.product.productservice.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(){

    }
    public ProductNotFoundException(String messsege){
        super(messsege);
    }
}
