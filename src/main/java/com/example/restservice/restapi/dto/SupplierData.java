package com.example.restservice.restapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SupplierData {

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Email is required")
    private String address;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
