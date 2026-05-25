package com.example.bai4.service;

import com.example.bai4.exception.BusinessException;
import com.example.bai4.model.entity.Order;
import com.example.bai4.model.entity.Promotion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderService {
    private final PromotionService promotionService;
    private List<Order> orders;

    public OrderService(PromotionService promotionService) {
        this.orders = new ArrayList<>();
        this.promotionService = promotionService;
    }

    public Order createOrder(Order order) {
        orders.add(order);
        return order;
    }

    public Order applyDiscount(Long orderId, String promotionCode) {
        // Tìm đơn hàng
        Order order = orders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Đơn hàng không tồn tại");
                    return new BusinessException("Đơn hàng không tồn tại");
                });
        // Tìm mã giảm giá
        Promotion promotion = promotionService.findByCode(promotionCode);
        // Áp dụng mã giảm giá
        order.setDiscountAmount(order.getTotalAmount() * promotion.getDiscountPercent() / 100);
        order.setFinalAmount(order.getTotalAmount() - order.getDiscountAmount());
        return order;
    }
}
