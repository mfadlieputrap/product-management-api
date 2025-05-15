package com.example.product_managemen_api.service;

import com.example.product_managemen_api.dto.ProductDTO;
import com.example.product_managemen_api.model.Product;
import com.example.product_managemen_api.repository.ProductRepository;
import com.example.product_managemen_api.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.product_managemen_api.util.TestUtility.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct1;
    private Product sampleProduct2;
    private List<Product> productList;

    @BeforeEach
    void setUp(){
        sampleProduct1 = new Product();
        sampleProduct1.setId(1L);
        sampleProduct1.setName("T-shirt");
        sampleProduct1.setPrice(BigDecimal.valueOf(120000));
        sampleProduct1.setStock(10);
        sampleProduct1.setDescription("Kaos polos");

        sampleProduct2 = new Product();
        sampleProduct2.setId(2L);
        sampleProduct2.setName("Shirt");
        sampleProduct2.setPrice(BigDecimal.valueOf(130000));
        sampleProduct2.setStock(15);
        sampleProduct2.setDescription("Kemeja polos");

        productList = List.of(sampleProduct1, sampleProduct2);
    }



    @Test
    public void testGetById_success(){
        //Arrange
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct1));

        ProductDTO result = productService.getProductById(1L);

        assertEquals("T-shirt", result.getName());
        assertEquals(BigDecimal.valueOf(120000), result.getPrice());
        assertEquals(10, result.getStock());
    }

    @Test
    public void testGetAllProducts_success(){
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        List<ProductDTO> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("T-shirt", result.getFirst().getName());
        assertEquals(BigDecimal.valueOf(120000), result.getFirst().getPrice());
        assertEquals(10, result.getFirst().getStock());
        assertEquals("Kaos polos", result.getFirst().getDescription());

        assertEquals("Shirt", result.get(1).getName());
        assertEquals(BigDecimal.valueOf(130000), result.get(1).getPrice());
        assertEquals(15, result.get(1).getStock());
        assertEquals("Kemeja polos", result.get(1).getDescription());
    }

    @Test
    public void testCreateProduct_success(){
        ProductDTO dto = buildProductDTO("Pant", BigDecimal.valueOf(115000), 15, "Celana");
        Product savedProduct = buildProduct(3L, "Pant", BigDecimal.valueOf(115000), 15, "Celana");

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(savedProduct);

        ProductDTO result = productService.createProduct(dto);

        assertEquals("Pant", result.getName());
        assertEquals(BigDecimal.valueOf(115000), result.getPrice());
        assertEquals(15, result.getStock());
        assertEquals("Celana", result.getDescription());
    }

    @Test
    public void testUpdateProduct_success(){
        ProductDTO dto = buildProductDTO("T-shirt", BigDecimal.valueOf(120000), 10, "Kaos polos");

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct1));

        Product updatedProduct = buildProduct(1L, "T-shirt", BigDecimal.valueOf(120000), 10, "Kaos polos");
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        ProductDTO result = productService.updateProduct(1L, dto);

        assertEquals("T-shirt", result.getName());
        assertEquals(BigDecimal.valueOf(120000), result.getPrice());
        assertEquals(10, result.getStock());
        assertEquals("Kaos polos", result.getDescription());
    }

    @Test
    public void testDeleteProduct_success(){
        productService.deleteProduct(1L);
    }
}
