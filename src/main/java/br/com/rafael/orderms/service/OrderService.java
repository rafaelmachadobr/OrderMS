package br.com.rafael.orderms.service;

import br.com.rafael.orderms.controller.dto.OrderResponse;
import br.com.rafael.orderms.entity.OrderEntity;
import br.com.rafael.orderms.entity.OrderItemEntity;
import br.com.rafael.orderms.listener.dto.OrderCreatedEvent;
import br.com.rafael.orderms.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {
        var entity = new OrderEntity();

        entity.setOrderId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        entity.setItems(getOrderItemEntities(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItemEntity> getOrderItemEntities(OrderCreatedEvent event) {
        return event.itens().stream()
                .map(i -> new OrderItemEntity(i.produto(), i.quantidade(), i.preco()))
                .toList();
    }
}
