package com.product.productservice.controller;

import com.product.productservice.dto.CategoryResponseDto;
import com.product.productservice.dto.CreateProductDto;
import com.product.productservice.dto.ProductResponseDto;
import com.product.productservice.exceptions.InvalidProductIdException;
import com.product.productservice.exceptions.ProductNotFoundException;
import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import com.product.productservice.repositoryLayer.projection.ProductProjection;
import com.product.productservice.service.ProductService;
import com.product.productservice.builder.ProductMapper;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService svc;
    private ProductMapper mapper;

    public ProductController(@Qualifier("selfproductservice") ProductService svc, ProductMapper mapper) {
        this.svc = svc;
        this.mapper = mapper;
    }

    @PostMapping("/product")
    public ProductResponseDto creatproduct(@RequestBody CreateProductDto dto) {

        //1.calling service layer by passing parameters
        Product pr = svc.createproduct(dto.getTitle(),
                dto.getDescription(),
                dto.getImage(),
                dto.getPrice(),
                dto.getCategory());
        return mapper.convertoproductresponsedto(pr);
    }

    @GetMapping("/productid/{id}")
    public ProductResponseDto getproductbyid(@PathVariable("id") Integer id)
            throws InvalidProductIdException, ProductNotFoundException {

        if (id == null) {
            throw new InvalidProductIdException("Product id is not corresct");
        }

        //1.call service layer
        Product pr = svc.getProductById(id);

        if (pr == null) {
            throw new ProductNotFoundException("product is not found");

        }

        //2.convert to client request
        ProductResponseDto response = mapper.convertoproductresponsedto(pr);

        //3.return
        return response;

    }

    @GetMapping("/products")
    public List<ProductResponseDto> getallproducts() {
        List<Product> productsList = svc.getallproducts();
        if (productsList == null || productsList.size() == 0) {
            System.out.print("something went wrong");
            return null;
        }
        List<ProductResponseDto> response = new ArrayList<>();
        for (Product pr : productsList) {
            response.add(mapper.convertoproductresponsedto(pr));
        }
        return response;
    }

    @DeleteMapping("/product/{id}")
    public Product deleteproductbyid(@PathVariable("id") int id) {

        Product pr = svc.deleteproductbyid(id);

        return pr;
    }

    @PatchMapping("/update/{id}")
    public ProductResponseDto updateproductbyid(@RequestBody CreateProductDto dto, @PathVariable("id") int id) {
        Product pr = svc.updateproductbyid(id,
                dto.getTitle(),
                dto.getDescription(),
                dto.getImage(),
                dto.getPrice(),
                dto.getCategory());

        return mapper.convertoproductresponsedto(pr);
    }

    @GetMapping("/product/{tittle}")
    public List<Product> getproductbytitle(@PathVariable("tittle") String tittle) {
        List<Product> pr = svc.productbytittle(tittle);
        return pr;
    }

    @GetMapping("products/{category}")
    public List<ProductProjection> getproductbycategory(@PathVariable("category") String category) {
        List<ProductProjection> products = svc.productbycategory(category);

        return products;
    }
}