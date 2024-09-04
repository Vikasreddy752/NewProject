package com.product.productservice.service;

import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import com.product.productservice.repositoryLayer.CategoryRepo;
import com.product.productservice.repositoryLayer.ProductRepo;
import com.product.productservice.repositoryLayer.projection.ProductProjection;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Qualifier("selfproductservice")
public class SelfProductService implements ProductService {


    private ProductRepo prodrepo;
    private CategoryRepo catrepo;
    private SelfCategoryService obj;

    @Autowired
    public SelfProductService(ProductRepo prodrepo, CategoryRepo catrepo, SelfCategoryService obj) {
        this.prodrepo = prodrepo;
        this.catrepo = catrepo;
        this.obj = obj;
    }

    @Override
    public Product getProductById(Integer id) {

        Optional<Product> pr = prodrepo.findById(id);
        return pr.get();
    }

    @Override

    public Product createproduct(String tittle, String description, String image, String price, String category) {
        Category existingcat = catrepo.findByTitle(category);
        if (existingcat == null) {
            existingcat = new Category();
            existingcat = obj.CreateCategory(category);
        }
        Product product = new Product();
        product.setTitle(tittle);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(Double.valueOf(price));
        product.setCategory(existingcat);
        product.setUpdatedAt(new Date());
        product.setDeleted(false);
        product.setCreatedAt(new Date());

        Product pro = prodrepo.save(product);
        return pro;
    }

    @Override
    public List<Product> getallproducts() {
        List<Product> pro = new ArrayList<>();
        pro = prodrepo.findAll();
        return pro;
    }

    @Override
    public Product deleteproductbyid(int id) {
         prodrepo.deleteById(id);
        return null;
    }

    @Override
    public Product updateproductbyid(int id, String tittle, String description, String image,
                                     String price, String category) {
        Optional<Product> pro = prodrepo.findById(id);
        Product product = pro.get();
        product.setTitle(tittle);
        product.setDescription(description);
        product.setImage(image);
        product.setPrice(Double.valueOf(price));
        // pro.setCategory();
        product.getCategory().setTitle(category);
        return prodrepo.save(product);
    }

    public List<Product> productbytittle(String tittle) {
        List<Product> products =  prodrepo.getproductbytittle(tittle);
        return products;
    }

    public List<ProductProjection> productbycategory(String category) {
        List<ProductProjection> pr = prodrepo.findByCategory(category);
       // pr.get(id).getId();
        return pr;
    }
}
