package com.example.ecommerce.service;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    public Page<Product> getAllProducts(Pageable pageable, String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Query query = new Query();
        List<Criteria> criteria = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            criteria.add(Criteria.where("name").regex(name, "i"));
        }
        
        if (minPrice != null) {
            criteria.add(Criteria.where("price").gte(minPrice));
        }
        
        if (maxPrice != null) {
            criteria.add(Criteria.where("price").lte(maxPrice));
        }
        
        if (!criteria.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        }
        
        // Apply sorting from pageable
        if (pageable.getSort().isSorted()) {
            pageable.getSort().forEach(order -> {
                query.with(Sort.by(order.getDirection(), order.getProperty()));
            });
        }
        
        long count = mongoTemplate.count(query, Product.class);
        
        // Apply pagination after counting total elements
        query.with(pageable);
        
        List<Product> products = mongoTemplate.find(query, Product.class);
        return new PageImpl<>(products, pageable, count);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }
}