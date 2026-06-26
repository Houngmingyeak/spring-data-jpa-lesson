package co.istad.ecommercespring.features.order.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateOrderRequest(

        @NotBlank(message = "Address is required")
        String address,

//        @NotBlank(message = "Discount is required")
        @NotNull(message = "Discount is required")
        @Min(0)
        @Max(100)
        Float discount,

        @Size(max = 255)
        String remark,

        @NotEmpty(message = "OrderLine is required")
        List<OrderLinesDto> orderLines
) {
}
