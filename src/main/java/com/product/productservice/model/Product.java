package com.product.productservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product extends BaseModel implements Serializable {

    //private Integer id;
    private String title;
    private String description;
    private double price;
    private String Image;
   // private String category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
