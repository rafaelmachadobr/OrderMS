package br.com.rafael.orderms.controller;

import br.com.rafael.orderms.controller.dto.ApiResponse;
import br.com.rafael.orderms.controller.dto.OrderResponse;
import br.com.rafael.orderms.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrdersByCustomerId(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(null);
    }
}
