package com.product.productservice.controller;

import com.product.productservice.builder.ProductMapper;
import com.product.productservice.dto.CategoryResponseDto;
import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import com.product.productservice.repositoryLayer.projection.ProductProjection;
import com.product.productservice.service.SelfCategoryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    private SelfCategoryService svc;
    private ProductMapper mapper;

    @Autowired
    public CategoryController(@Qualifier("selfcategoryservice") SelfCategoryService svc, ProductMapper mapper) {
        this.svc = svc;
        this.mapper = mapper;
    }

    @GetMapping("/categorys")
    public List<CategoryResponseDto> getallcategoriess(){
        List<Category> categoriessvc=svc.getallcategories();
        List<CategoryResponseDto> respons = new ArrayList<>();
        for (Category c : categoriessvc) {
            respons.add(mapper.convertocategoryresponsedto(c));
        }
        return respons;
    }

}
