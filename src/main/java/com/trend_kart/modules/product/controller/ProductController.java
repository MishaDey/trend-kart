package com.trend_kart.modules.product.controller;

import com.trend_kart.modules.product.dto.ProductDTO;
import com.trend_kart.modules.product.dto.ProductDocumentDTO;
import com.trend_kart.modules.product.service.ProductDocumentService;
import com.trend_kart.modules.product.service.implementation.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceImpl productService;
    private final ProductDocumentService productDocumentService;

    public ProductController(ProductServiceImpl productService, ProductDocumentService productDocumentService) {
        this.productService = productService;
        this.productDocumentService = productDocumentService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) throws IOException {
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(productService.findProduct(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDocumentDTO>> search(@RequestParam("pageSize") Integer pageSize,
                                                           @RequestParam("pageNo") Integer pageNo,
                                                           @RequestParam("searchKey") String searchKey,
                                                           @RequestParam("brand") String brand,
                                                           @RequestParam("maxPrice") Double maxPrice) throws IOException {
        return ResponseEntity.ok(productDocumentService.searchProducts(pageSize, pageNo, searchKey, brand, maxPrice));
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<String>> search(@RequestParam("searchKey") String searchKey) throws IOException {
        return ResponseEntity.ok(productDocumentService.autoCompleteProductNames(searchKey));
    }
}
