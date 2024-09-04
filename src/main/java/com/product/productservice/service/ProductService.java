package com.product.productservice.service;

import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import com.product.productservice.repositoryLayer.projection.ProductProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public Product getProductById(Integer id);
    public Product createproduct(String tittle, String descreption, String image, String price, String category);
    public List<Product> getallproducts();
    public Product deleteproductbyid(int id);
    public Product updateproductbyid(int id, String tittle, String descreption, String image, String price, String category);
    public List<Product> productbytittle(String tittle);
    public List<ProductProjection> productbycategory(String category);
}
