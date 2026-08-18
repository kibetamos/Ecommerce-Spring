package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Getter
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @GetMapping
    public Iterable<ProductDto> getAllProducts(
           @RequestHeader (name="x-auth-token") String authToken,
            @RequestParam(required = false, name="categoryId") Byte categoryId

    ){
        System.out.println(authToken);
        List<Product> products;

        if(categoryId != null){
            products = productRepository.findAllByCategoryId(categoryId);

        } else {
            products = productRepository.findWithCategory();
        }
        return products.stream().map((productMapper::toDto)).toList();


    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        var product = productRepository.findById(id).orElse(null);

        if (product == null){
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.ok(productMapper.toDto(product));


    }
}
