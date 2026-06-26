package co.istad.ecommercespring.features.order;

import co.istad.ecommercespring.features.order.dto.CreateOrderRequest;
import co.istad.ecommercespring.features.order.dto.OrderResponse;
import co.istad.ecommercespring.features.product.Product;
import co.istad.ecommercespring.features.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {

//        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);
        final Order order = new Order();
        order.setAddress(createOrderRequest.address());
        order.setDiscount(createOrderRequest.discount());
        order.setRemark(createOrderRequest.remark());

//        validate order line (List)
        List<OrderLine> orderLines = new ArrayList<>();
        boolean isValidOder = createOrderRequest.orderLines().stream()
                .allMatch(orderLineDto -> {
                    Optional<Product> productOptional = productRepository.findByCode(orderLineDto.code());

                    if (productOptional.isPresent()) {
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(productOptional.get());
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setUnitPrice(orderLineDto.unitPrice());
                        orderLines.add(orderLine);
                        return true;
                    }
                    return false;
                });
        if (!isValidOder) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order line");
        }

        order.setOrderLines(orderLines);
//        security related
        order.setCustomerId("Mingyeak");

        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);

        Order savedOrder = orderRepository.save(order);
//        orderRepository.save(order);
        return orderMapper.mapOrderToOrderResponse(savedOrder);
    }

    @Override
    public Page<OrderResponse> findAll(int page, int size) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(page, size, sortById);
        Page<Order> orders = orderRepository.findAll(pageRequest);

        return orders.map(orderMapper::mapOrderToOrderResponse);
    }

    @Override
    public OrderResponse findById(UUID id) {
        Order order = orderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found with id: " + id
                ));
        return orderMapper.mapOrderToOrderResponse(order);
    }


    @Override
    public void softDelete(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found with id: " + id
                ));
        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public void hardDelete(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found with id: " + id
                ));
        orderRepository.delete(order);
    }

    @Override
    public void setStatus(UUID id, Boolean status) {
        Order order = orderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Order not found with id: " + id
                ));
        order.setStatus(true);
        orderRepository.save(order);

    }
}
