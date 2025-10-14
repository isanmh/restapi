package com.example.restservice.restapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.restservice.restapi.entities.Category;
import com.example.restservice.restapi.repositories.CategoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category findOne(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (!category.isPresent()) {
            // Handle the case where the category is not found
            try {
                throw new Exception("Category not found with id: " + id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return category.get();
    }

    public void removeOne(Long id) {
        categoryRepo.deleteById(id);
    }

    // custom method pagination and search
    public Iterable<Category> findByName(String name, Pageable pageable) {
        return categoryRepo.findByNameContains(name, pageable);
    }

    // Save Batch
    public Iterable<Category> saveBatch(Iterable<Category> categories) {
        return categoryRepo.saveAll(categories);
    }

}
