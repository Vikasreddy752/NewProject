package com.product.productservice.service;

import com.product.productservice.dto.FakeStoreProductDto;
import com.product.productservice.model.Category;
import com.product.productservice.model.Product;
import com.product.productservice.builder.ProductMapper;
import com.product.productservice.repositoryLayer.projection.ProductProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("fakestoreservice")
public class FakeStoreService implements ProductService {

    private RestTemplate restTemplate;
    private ProductMapper mapper;

    public FakeStoreService(RestTemplate restTemplate, ProductMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public Product getProductById(Integer id) {
        //1.call to api
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity("http://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        //2.getbody
        FakeStoreProductDto dto = response.getBody();

        if (response == null || response.getBody() == null) {
            return null;
        }

        //3.return
        return mapper.toproduct(dto);
    }

    @Override
    public Product createproduct(String tittle, String descreption, String image, String price, String category) {
        //1.create new fakestoreproductdto to convert into jason
        FakeStoreProductDto requestbody = new FakeStoreProductDto();
        requestbody.setTitle(tittle);
        requestbody.setDescription(descreption);
        requestbody.setImage(image);
        requestbody.setPrice(price);
        requestbody.setCategory(category);

        //2.call to api
        FakeStoreProductDto response =
                restTemplate.postForObject("http://fakestoreapi.com/products/", requestbody, FakeStoreProductDto.class);

        //convert to product
        Product pr = mapper.toproduct(response);

        return pr;
    }

    @Override
    public List<Product> getallproducts() {

        List<Product> products = new ArrayList<>();

        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity("http://fakestoreapi.com/products/", FakeStoreProductDto[].class);

        FakeStoreProductDto[] dto = response.getBody();

        if (dto == null || dto.length == 0) {
            System.out.print("Something went wrong");
            return new ArrayList<>();
        }
        for (FakeStoreProductDto dtos : dto) {
            Product product = mapper.toproduct(dtos);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product deleteproductbyid(int id) {

        ResponseEntity<FakeStoreProductDto> response
                = restTemplate.getForEntity("http://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        FakeStoreProductDto dto = response.getBody();

        return mapper.toproduct(dto);
    }

    @Override
    public Product updateproductbyid(int id, String tittle, String descreption, String image, String price, String category) {
        FakeStoreProductDto requestbody = new FakeStoreProductDto();
        requestbody.setTitle(tittle);
        requestbody.setDescription(descreption);
        requestbody.setCategory(category);
        requestbody.setPrice(price);

        FakeStoreProductDto response
                = restTemplate.patchForObject("http://fakestoreapi.com/products/" + id, requestbody, FakeStoreProductDto.class);

        Product products = mapper.toproduct(response);
        return products;
    }
    public List<Product> productbytittle(String tittle){
        return null;
    }
    public List<ProductProjection> productbycategory(String category){
        return null;
    }
}
