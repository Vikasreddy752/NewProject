package com.product.productservice.builder;

import com.product.productservice.dto.CategoryResponseDto;
import com.product.productservice.dto.FakeStoreProductDto;
import com.product.productservice.dto.ProductResponseDto;
import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import org.springframework.stereotype.Component;

@Component
public  class ProductMapper {
    public ProductResponseDto convertoproductresponsedto(Product pr){
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(pr.getId());
        dto.setTitle((pr.getTitle()));
        dto.setDescription(pr.getDescription());
        dto.setImage(pr.getImage());
        dto.setPrice(pr.getPrice());
        dto.setCategory(pr.getCategory().getTitle());

        return dto;
    }
    public Product toproduct(FakeStoreProductDto dto) {
        Product pr = new Product();
        //Category cr = new Category();
       // cr.setTitle(dto.getCategory());//for category

       // pr.setCategory(dto.getCategory());
        pr.setId(dto.getId());
        pr.setTitle(dto.getTitle());
        pr.setDescription(dto.getDescription());
        pr.setImage(dto.getImage());
        pr.setPrice(Double.valueOf(dto.getPrice()));

        return pr;

    }
    public CategoryResponseDto convertocategoryresponsedto(Category cat) {
        CategoryResponseDto dto  = new CategoryResponseDto();
        dto.setTittle(cat.getTitle());
        return dto;
    }

}
