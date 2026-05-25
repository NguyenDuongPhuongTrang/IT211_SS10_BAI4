package com.example.bai4.controller;

import com.example.bai4.model.dto.response.ApiDataResponse;
import com.example.bai4.model.entity.Order;
import com.example.bai4.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Order>> createOrder(Order order) {
        return new ResponseEntity<>(new ApiDataResponse<>(true, "Thành công", orderService.createOrder(order), null, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
