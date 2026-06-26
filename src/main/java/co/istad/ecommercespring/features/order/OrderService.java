package co.istad.ecommercespring.features.order;

import co.istad.ecommercespring.features.order.dto.CreateOrderRequest;
import co.istad.ecommercespring.features.order.dto.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {

    OrderResponse createNew(CreateOrderRequest createOrderRequest);

    Page<OrderResponse> findAll(int page, int size);

    OrderResponse findById(UUID id);

    void softDelete(UUID id);

    void hardDelete(UUID id);

     void setStatus(UUID id, Boolean status);

}
