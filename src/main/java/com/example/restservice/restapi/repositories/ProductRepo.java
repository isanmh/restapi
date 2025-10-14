package com.example.restservice.restapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.restservice.restapi.entities.Product;

public interface ProductRepo extends CrudRepository<Product, Long> {

}
