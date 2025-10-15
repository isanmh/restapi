package com.example.restservice.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.restapi.entities.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

}
