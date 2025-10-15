package com.example.restservice.restapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryData {

    @NotEmpty(message = "Name is required")
    private String name;

}
