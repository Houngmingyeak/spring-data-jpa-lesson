package co.istad.ecommercespring.exception;

import lombok.Builder;

@Builder
public record FieldErrorResponse(
        String field,
        String message
) {
}