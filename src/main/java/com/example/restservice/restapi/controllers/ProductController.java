package com.example.restservice.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.restapi.entities.Product;
import com.example.restservice.restapi.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // add product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    // get all products
    @GetMapping
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    // get product by id
    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    // delete product by id
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
        productService.removeOne(id);
    }

    // update
    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.save(product);
    }

}
