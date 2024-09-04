package com.product.productservice.repositoryLayer;

import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import com.product.productservice.repositoryLayer.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {


    Category findByTitle(String title);

}
