package com.trend_kart.modules.product.service.implementation;

import com.trend_kart.modules.product.dto.ProductDTO;
import com.trend_kart.modules.product.dto.ProductDocumentDTO;
import com.trend_kart.modules.product.entity.Product;
import com.trend_kart.modules.product.entity.ProductDocument;
import com.trend_kart.modules.product.repo.ProductRepository;
import com.trend_kart.modules.product.service.ProductDocumentService;
import com.trend_kart.modules.product.service.ProductService;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDocumentService productDocumentService;

    ProductServiceImpl(ProductRepository productRepository, ProductDocumentService productDocumentService) {
        this.productRepository = productRepository;
        this.productDocumentService = productDocumentService;
    }

    @Override
    public List<ProductDTO> listProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::productToProductDTO).toList();
    }

    @Override
    public ProductDTO findProduct(UUID id) {
        return productToProductDTO(Objects.requireNonNull(productRepository.findById(id).orElse(null)));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) throws IOException {
        Product product = productRepository.save(productDTOToProduct(productDTO));
        ProductDocument productDocument = productToProductDocument(product);
        productDocumentService.indexProduct(productDocument);
        return productToProductDTO(product);
    }

    private ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .brand(product.getBrand())
                .tags(product.getTags())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private Product productDTOToProduct(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .code(productDTO.getCode())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .category(productDTO.getCategory())
                .brand(productDTO.getBrand())
                .tags(productDTO.getTags())
                .build();
    }

    private ProductDocument productToProductDocument(Product product) {
        return ProductDocument.builder()
                .id(product.getId().toString())
                .productID(product.getId().toString())
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .brand(product.getBrand())
                .tags(product.getTags())
                .build();
    }
}
