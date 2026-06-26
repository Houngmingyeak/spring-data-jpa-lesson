package co.istad.ecommercespring.features.order.dto;

import co.istad.ecommercespring.features.order.OrderLine;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(

        UUID id,
        String customerId,
        String address,
        Float discount,
        String remark,
        Boolean status,
        LocalDateTime createdAt,
        Boolean isDeleted

) {
}
