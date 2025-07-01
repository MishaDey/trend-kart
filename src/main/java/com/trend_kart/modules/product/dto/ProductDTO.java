package com.trend_kart.modules.product.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO implements Serializable {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private Double price;
    private String category;
    private String brand;
    private int stock;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
