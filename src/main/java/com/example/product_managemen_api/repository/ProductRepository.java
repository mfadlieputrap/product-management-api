package com.example.product_managemen_api.repository;

import com.example.product_managemen_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
