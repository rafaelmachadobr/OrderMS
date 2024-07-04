package br.com.rafael.orderms.controller;

import br.com.rafael.orderms.controller.dto.ApiResponse;
import br.com.rafael.orderms.controller.dto.OrderResponse;
import br.com.rafael.orderms.controller.dto.PaginationResponse;
import br.com.rafael.orderms.service.OrderService;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrdersByCustomerId(
            @PathVariable Long customerId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        var pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));

        return ResponseEntity.ok(new ApiResponse<>
                (pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }
}
