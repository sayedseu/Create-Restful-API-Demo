package com.example.restapidemo.controller;

import com.example.restapidemo.exception.ResourceAlreadyExistsException;
import com.example.restapidemo.exception.ResourceDoesNotFoundException;
import com.example.restapidemo.model.Product;
import com.example.restapidemo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> retrieveByID(@PathVariable long id) {
        try {
            Product product = service.retrieve(id);
            return ResponseEntity.ok(product);
        } catch (ResourceDoesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Product>> retrieveAllProduct() {
        try {
            List<Product> productList = service.retrieve();
            return ResponseEntity.ok(productList);
        } catch (ResourceDoesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        try {
            Product savedProduct = service.insert(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Product> updateByID(@PathVariable long id, @RequestBody Product product) {
        try {
            Product updatedProduct = service.update(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (ResourceDoesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteByID(@PathVariable long id) {
        try {
            boolean deleted = service.delete(id);
            return ResponseEntity.ok(id);
        } catch (ResourceDoesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
