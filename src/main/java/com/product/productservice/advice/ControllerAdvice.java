package com.product.productservice.advice;

import com.product.productservice.dto.ErrorDto;
import com.product.productservice.exceptions.InvalidProductIdException;
import com.product.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ErrorDto> handleInvalidproductexception(InvalidProductIdException e){
        ErrorDto dto = new ErrorDto();
        dto.setCode("some error code");
        dto.setMessege(e.getMessage());

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleInvalidproductnotfoundexception(ProductNotFoundException e){
       ErrorDto dto = new ErrorDto();
       dto.setCode("some error code");
       dto.setMessege("product not found");

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
