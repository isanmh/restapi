package com.example.restservice.restapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.restapi.entities.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

    // Query Drived Method
    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    // cari berdasarkan name atau email
    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
