package com.trend_kart.modules.product.service;

import com.trend_kart.modules.product.dto.ProductDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    public List<ProductDTO> listProducts();
    public ProductDTO findProduct(UUID id);
    public ProductDTO saveProduct(ProductDTO productDTO) throws IOException;
}
