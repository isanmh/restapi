package com.example.restservice.restapi.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
// @Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    // Relasi dengan Supplier (Many-to-Many)
    @ManyToMany
    // @JsonManagedReference
    // untuk membuat table perantara relasi many to many
    @JoinTable(name = "product_suppliers", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    // private Set<Supplier> suppliers;
    private List<Supplier> suppliers;

}
