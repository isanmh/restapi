package com.example.restservice.restapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.restapi.entities.Supplier;
import com.example.restservice.restapi.repositories.SupplierRepo;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Iterable<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if (!supplier.isPresent()) {
            throw new RuntimeException("Supplier with id " + id + " not found");
        }
        return supplier.get();
    }

    public void delete(Long id) {
        supplierRepo.deleteById(id);
    }

}
