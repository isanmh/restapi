package com.example.restservice.restapi.controllers;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.restapi.dto.CategoryData;
import com.example.restservice.restapi.dto.ResponseData;
import com.example.restservice.restapi.dto.SearchData;
import com.example.restservice.restapi.entities.Category;
import com.example.restservice.restapi.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (var err : errors.getAllErrors()) {
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.badRequest().body(responseData);
        }

        // model mapper
        // Category category = new Category();
        // category.setName(categoryData.getName());

        var category = modelMapper.map(categoryData, Category.class);

        responseData.setStatus(true);
        responseData.getMessages().add("Category created successfully");
        responseData.setData(categoryService.save(category));
        return ResponseEntity.ok(responseData);
    }

    // get all categories
    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable("id") Long id) {
        return categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody Category category, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (var err : errors.getAllErrors()) {
                responseData.getMessages().add(err.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.badRequest().body(responseData);
        }

        var updatedCategory = modelMapper.map(category, Category.class);

        responseData.setStatus(true);
        responseData.getMessages().add("Category updated successfully");
        responseData.setData(categoryService.save(updatedCategory));
        return ResponseEntity.ok(responseData);
    }

    // pagable category
    @GetMapping("/{page}/{size}")
    public Iterable<Category> findAllPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        return categoryService.findAllPage(PageRequest.of(page, size));
    }

    // save batch
    @PostMapping("/add-batch")
    public ResponseEntity<ResponseData<Iterable<Category>>> createBatch(@RequestBody Category[] categories) {

        ResponseData<Iterable<Category>> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.getMessages().add("Categories Batch created successfully");
        responseData.setData(categoryService.saveBatch(Arrays.asList(categories)));

        return ResponseEntity.ok(responseData);
    }

    // serach by name with pagination
    @PostMapping("/search/{page}/{size}/{sort}")
    public Iterable<Category> findByName(@RequestBody SearchData searchData, @PathVariable("page") int page,
            @PathVariable("size") int size,
            @PathVariable("sort") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        // sorting data
        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }
        return categoryService.findByName(searchData.getSearchKey(), pageable);

    }
}
