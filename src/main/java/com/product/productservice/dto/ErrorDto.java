package com.product.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {
    private String messege;
    private String code;

    public ErrorDto(){

    }
}
