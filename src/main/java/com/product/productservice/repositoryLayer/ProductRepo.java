package com.product.productservice.repositoryLayer;

import com.product.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product save(Product product);

    @Query("select p from Product p where p.title = :tittle ")
    List<Product> getproductbytittle(String tittle);
}
