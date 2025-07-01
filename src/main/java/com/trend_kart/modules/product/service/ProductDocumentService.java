package com.trend_kart.modules.product.service;

import com.trend_kart.modules.product.dto.ProductDocumentDTO;
import com.trend_kart.modules.product.entity.ProductDocument;

import java.io.IOException;
import java.util.List;

public interface ProductDocumentService {
    public void indexProduct(ProductDocument productDocument) throws IOException;
    public List<ProductDocumentDTO> searchProducts(Integer pageSize, Integer pageNo, String searchKey, String brand, Double maxPrice) throws IOException;
    public List<String> autoCompleteProductNames(String searchKey) throws IOException;
}
