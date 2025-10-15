package com.example.restservice.restapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.restapi.dto.ResponseData;
import com.example.restservice.restapi.entities.Product;
import com.example.restservice.restapi.entities.Supplier;
import com.example.restservice.restapi.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // add product
    @PostMapping
    public ResponseEntity<ResponseData<Product>> addProduct(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        // jika ada error
        if (errors.hasErrors()) {
            for (var error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.badRequest().body(responseData);
        }

        responseData.setStatus(true);
        responseData.getMessages().add("Product created successfully");
        responseData.setData(productService.save(product));

        return ResponseEntity.status(201).body(responseData);
    }

    // get all products
    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Product>>> findAll() {
        ResponseData<Iterable<Product>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("List of products");
        responseData.setData(productService.findAll());
        return ResponseEntity.ok(responseData);
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
    public ResponseEntity<ResponseData<Product>> updateProduct(@PathVariable("id") Long id,
            @Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        // jika ada error
        if (errors.hasErrors()) {
            for (var error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.badRequest().body(responseData);
        }

        product.setId(id);
        responseData.setStatus(true);
        responseData.getMessages().add("Product updated successfully");
        responseData.setData(productService.save(product));

        return ResponseEntity.status(200).body(responseData);
    }

    // end point add supplier to product
    @PostMapping("/{id}/suppliers")
    public ResponseEntity<ResponseData<Product>> addSupplier(@PathVariable("id") Long productId,
            @RequestBody Supplier supplier) {

        productService.addSupplier(supplier, productId);
        ResponseData<Product> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.getMessages().add("Supplier added to product successfully");
        responseData.setData(productService.findOne(productId));
        return ResponseEntity.ok(responseData);
    }
}
