package br.com.rafael.orderms.listener.dto;

import br.com.rafael.orderms.entity.OrderItemEntity;

import java.util.List;

public record OrderCreatedEvent(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemEvent> itens
) {
}
