package com.example.restservice.restapi.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Product name must not be empty")
    @Column(name = "product_name", length = 100)
    private String name;

    @NotEmpty(message = "Product description must not be empty")
    @Column(name = "product_description", length = 255)
    private String description;

    private Double price;

    // Relasi dengan Category (Many-to-One)
    @ManyToOne
    private Category category;

}
