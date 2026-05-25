package com.example.bai4.model.entity;

import com.example.bai4.validation.DiscountPercent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Promotion {
    private Long id;
    private String code;
    @DiscountPercent(message = "Discount percent must be between 0 and 100")
    private Integer discountPercent;
    private Boolean isActive;
}
