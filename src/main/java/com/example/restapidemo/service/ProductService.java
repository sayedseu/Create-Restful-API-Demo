package com.example.restapidemo.service;

import com.example.restapidemo.exception.ResourceAlreadyExistsException;
import com.example.restapidemo.exception.ResourceDoesNotFoundException;
import com.example.restapidemo.model.Product;
import com.example.restapidemo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product insert(Product product) throws ResourceAlreadyExistsException {
        Optional<Product> productOptional = repository.findById(product.getId());
        if (productOptional.isPresent()){
            throw new ResourceAlreadyExistsException(String.valueOf(product.getId()));
        }else {
            return repository.save(product);
        }
    }
    public Product retrieve(long id) throws ResourceDoesNotFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }else {
            throw new ResourceDoesNotFoundException(String.valueOf(id));
        }
    }
    public List<Product> retrieve() throws ResourceDoesNotFoundException {
        List<Product> productList = repository.findAll();
        if (productList == null){
            throw new ResourceDoesNotFoundException("");
        }else {
            return productList;
        }
    }
    public Product update(long id,Product product) throws ResourceDoesNotFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()){
            product.setId(id);
            return repository.save(product);
        }else {
            throw new ResourceDoesNotFoundException(String.valueOf(id));
        }
    }
    public boolean delete(long id) throws ResourceDoesNotFoundException {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()){
            repository.deleteById(id);
            return true;
        }else {
            throw new ResourceDoesNotFoundException(String.valueOf(id));
        }
    }
}
