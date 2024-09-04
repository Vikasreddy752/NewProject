package com.product.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category extends BaseModel implements Serializable {
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    List<Product> products;

}
