package br.com.rafael.orderms.listener.dto;

public record OrderItemEvent(
        String produto,
        Integer quantidade,
        Double preco
) {
}
