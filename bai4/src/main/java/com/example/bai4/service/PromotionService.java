package com.example.bai4.service;

import com.example.bai4.exception.BusinessException;
import com.example.bai4.model.entity.Promotion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PromotionService {
    private List<Promotion> promotions;

    public PromotionService() {
        this.promotions = new ArrayList<>();
    }

    public Promotion createPromotion(Promotion promotion) {
        promotions.add(promotion);
        return promotion;
    }

    public Promotion findByCode(String code) {
        return promotions.stream()
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Mã giảm giá không tồn tại");
                    return new BusinessException("Mã giảm giá không tồn tại");
                });
    }
}
