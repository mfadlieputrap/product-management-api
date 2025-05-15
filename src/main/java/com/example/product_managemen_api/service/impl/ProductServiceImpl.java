package com.example.product_managemen_api.service.impl;

import com.example.product_managemen_api.dto.ProductDTO;
import com.example.product_managemen_api.exception.ResourceNotFoundException;
import com.example.product_managemen_api.model.Product;
import com.example.product_managemen_api.repository.ProductRepository;
import com.example.product_managemen_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    private ProductDTO mapToDTO(Product product){
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getDescription());
    }

    private Product mapToEntity(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setDescription(dto.getDescription());

        return product;
    }

    @Override
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO){
        Product product = mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());
        Product productUpdated = productRepository.save(product);
        return mapToDTO(productUpdated);
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
