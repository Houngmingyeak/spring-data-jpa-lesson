package co.istad.ecommercespring.features.category.dto;

import jakarta.validation.constraints.Size;

public record UpdateCategoryRequest(

        @Size(max = 50)
        String name,

        String description,

        @Size(max = 255)
        String icon
) {}