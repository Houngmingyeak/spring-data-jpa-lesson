package co.istad.ecommercespring.features.product.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 255)
        String name,

        @Size(max = 500)
        String description,

        @Size(max = 255)
        String thumbnail,

        @NotNull(message = "Unit price is required")
        @Min(0)
        BigDecimal unitPrice,

        @NotNull (message = "Quantity is required")
        @Min(0)
        Integer qty,

        @NotNull(message = "Category is required")
        @Positive
        Integer categoryId
) {
}
