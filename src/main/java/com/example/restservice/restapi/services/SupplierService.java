package com.example.restservice.restapi.services;

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
        return supplierRepo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        supplierRepo.deleteById(id);
    }

}
