package com.product.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDto {
    private String title;
    private String description;
    private String price;
    private String image;
    private String category;
}