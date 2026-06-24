package co.istad.ecommercespring.features.product.dto;

import co.istad.ecommercespring.features.category.dto.CategorySnippetResponse;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String slug, //slug = name, but slug use snake case and use to improve SEO
        String name,
        String description,
        String thumbnail,
        BigDecimal unitPrice,
        Integer qty,
        Boolean isAvailable,
        Boolean isDeleted,
        CategorySnippetResponse category
) {
}
