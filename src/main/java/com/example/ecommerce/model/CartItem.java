package com.example.ecommerce.model;

import lombok.Data;

@Data
public class CartItem {
    private String productId;
    private int quantity;
    private transient Product product; // For temporary product data
}