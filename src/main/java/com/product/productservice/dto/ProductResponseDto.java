package com.product.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private Integer id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category ;

}
