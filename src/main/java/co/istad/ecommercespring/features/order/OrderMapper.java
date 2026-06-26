package co.istad.ecommercespring.features.order;

import co.istad.ecommercespring.features.order.dto.CreateOrderRequest;
import co.istad.ecommercespring.features.order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface OrderMapper {

    OrderResponse mapOrderToOrderResponse(Order order);

    Order mapCreateOrderRequestToOrder(CreateOrderRequest createOrderRequest);

}