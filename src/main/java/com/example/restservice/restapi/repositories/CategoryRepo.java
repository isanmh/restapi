package com.example.restservice.restapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.restservice.restapi.entities.Category;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long>, CrudRepository<Category, Long> {

    Page<Category> findByNameContains(String name, Pageable pageable);

    Page<Category> findAll(Pageable pageable);

    // api/{page}/{size}

}
