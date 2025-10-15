package com.example.restservice.restapi.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.restapi.dto.ResponseData;
import com.example.restservice.restapi.dto.SupplierData;
import com.example.restservice.restapi.entities.Supplier;
import com.example.restservice.restapi.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(
            @Valid @RequestBody SupplierData supplierData, Errors errors) {

        ResponseData<Supplier> responseData = new ResponseData<>();
        // jika ada error
        if (errors.hasErrors()) {
            for (var error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setData(null);
            return ResponseEntity.badRequest().body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        responseData.setStatus(true);
        responseData.getMessages().add("Supplier created successfully");
        responseData.setData(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id) {
        return supplierService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        supplierService.delete(id);
    }
}