package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseLoader {
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() throws IOException {
        if (productRepository.count() == 0) {
            ClassPathResource resource = new ClassPathResource("products.json");
            try (InputStream inputStream = resource.getInputStream()) {
                List<Product> products = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
                productRepository.saveAll(products);
            }
        }
    }
}