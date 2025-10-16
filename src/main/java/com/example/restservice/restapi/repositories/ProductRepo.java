package com.example.restservice.restapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.restservice.restapi.entities.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    // Query manual jika diperlukan
    @Query("SELECT p FROM p Product WHERE p.name= :name")
    List<Product> findByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameLike(@Param("name") String name);

    @Query("SELECT p FROM Product p JOIN p.supplierId s WHERE s.id = :supplierId")
    List<Product> findBySupplierId(@Param("supplierId") Long supplierId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    List<Product> findBySupplier(@Param("supplier") Long supplierId);

}
