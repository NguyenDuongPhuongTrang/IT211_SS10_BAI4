package com.example.bai4.controller;

import com.example.bai4.exception.BusinessException;
import com.example.bai4.model.dto.response.ApiDataResponse;
import com.example.bai4.model.entity.Order;
import com.example.bai4.model.entity.Promotion;
import com.example.bai4.service.OrderService;
import com.example.bai4.service.PromotionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/pro")
public class PromotionController {
    private final PromotionService promotionService;
    private final OrderService orderService;

    public PromotionController(PromotionService promotionService, OrderService orderService) {
        this.promotionService = promotionService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Promotion>> createPromotion(@Valid @RequestBody Promotion promotion) {
        log.info("Promotion created: {}", promotion);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thành công",
                promotionService.createPromotion(promotion),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiDataResponse<Order>> applyDiscount(@RequestBody Long order, @RequestBody String promotionCode) {
        if (promotionCode.equalsIgnoreCase("EXPIRED")) {
            throw new BusinessException("Mã giảm giá đã hết hạn");
        }
        if (promotionCode.equalsIgnoreCase("CRASH")) {
            throw new NullPointerException();
        }
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thành công",
                orderService.applyDiscount(order, promotionCode),
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}