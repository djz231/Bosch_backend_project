package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "API for product operations")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(
        summary = "Get all products with pagination and filtering",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved products"),
            @ApiResponse(responseCode = "400", description = "Invalid parameters")
        }
    )
    public ResponseEntity<Page<Product>> getAllProducts(
            @Parameter(description = "Pagination and sorting parameters") Pageable pageable,
            @Parameter(description = "Filter by product name (partial match)") 
            @RequestParam(required = false) String name,
            @Parameter(description = "Minimum price filter") 
            @RequestParam(required = false) BigDecimal minPrice,
            @Parameter(description = "Maximum price filter") 
            @RequestParam(required = false) BigDecimal maxPrice) {
        
        return ResponseEntity.ok(productService.getAllProducts(pageable, name, minPrice, maxPrice));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "ID of the product to be retrieved") 
            @PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}