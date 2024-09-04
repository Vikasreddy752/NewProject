package com.product.productservice.service;

import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import com.product.productservice.repositoryLayer.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("selfcategoryservice")
public class SelfCategoryService {

    @Autowired
    private CategoryRepo obj;

    public Category CreateCategory(String tittle) {
        Category category = new Category();
        // category.setId(id);
        category.setTitle(tittle);
        return obj.save(category);
    }

    public List<Category> getallcategories() {
        List<Category> cat = new ArrayList<>();
        cat = obj.findAll();
        return cat;
    }
}

