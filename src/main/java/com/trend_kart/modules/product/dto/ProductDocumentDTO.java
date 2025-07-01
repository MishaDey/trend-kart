package com.trend_kart.modules.product.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ProductDocumentDTO implements Serializable {
    private String id;
    private String productID;
    private String name;
    private String code;
    private String description;
    private Double price;
    private String category;
    private String brand;
    private String[] tags;
}
