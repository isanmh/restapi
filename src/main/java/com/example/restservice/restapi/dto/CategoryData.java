package com.example.restservice.restapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryData {

    @NotEmpty(message = "Name is required")
    private String name;
}
