package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartItemDto;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@Valid @RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartService.addToCart(cartItemDto));
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> updateCartItem(@PathVariable String id, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateCartItem(id, quantity));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<?> removeFromCart(@PathVariable String id) {
        cartService.removeFromCart(id);
        return ResponseEntity.ok().build();
    }
}