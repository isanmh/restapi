package com.example.restservice.restapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.restapi.entities.Product;
import com.example.restservice.restapi.entities.Supplier;
import com.example.restservice.restapi.repositories.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    // inject repo
    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

    // add supplier to product
    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);

        if (product == null) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        product.getSuppliers().add(supplier);
        save(product);
    }
}
