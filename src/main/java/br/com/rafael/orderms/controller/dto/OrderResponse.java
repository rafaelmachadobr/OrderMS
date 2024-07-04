package br.com.rafael.orderms.controller.dto;

import br.com.rafael.orderms.entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,
        Long customerId,
        BigDecimal total
) {
    public static OrderResponse fromEntity(OrderEntity orderEntity) {
        return new OrderResponse(
                orderEntity.getOrderId(),
                orderEntity.getCustomerId(),
                orderEntity.getTotal()
        );
    }
}
