package com.example.product_managemen_api.util;

import com.example.product_managemen_api.dto.ProductDTO;
import com.example.product_managemen_api.model.Product;

import java.math.BigDecimal;

public class TestUtility {

    public static Product buildProduct(Long id, String name, BigDecimal price, Integer stock, String description){
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setDescription(description);
        return product;
    }

    public static ProductDTO buildProductDTO(String name, BigDecimal price, Integer stock, String description){
        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setPrice(price);
        dto.setStock(stock);
        dto.setDescription(description);
        return dto;
    }
}
