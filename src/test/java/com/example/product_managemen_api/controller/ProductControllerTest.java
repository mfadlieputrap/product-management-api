package com.example.product_managemen_api.controller;

import com.example.product_managemen_api.dto.ProductDTO;
import com.example.product_managemen_api.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProduct() throws Exception{
        ProductDTO product1 = new ProductDTO(1L, "T-shirt", BigDecimal.valueOf(120000), 10, "Kaos polos");
        ProductDTO product2 = new ProductDTO(2L, "Shoes", BigDecimal.valueOf(200000), 5, "Sepatu olahraga");

        List<ProductDTO> products = List.of(product1,product2);

        Mockito.when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("T-shirt"))
                .andExpect(jsonPath("$[0].price").value(BigDecimal.valueOf(120000)))
                .andExpect(jsonPath("$[0].stock").value(10))
                .andExpect(jsonPath("$[0].description").value("Kaos polos"))
                .andExpect(jsonPath("$[1].name").value("Shoes"))
                .andExpect(jsonPath("$[1].price").value(BigDecimal.valueOf(200000)))
                .andExpect(jsonPath("$[1].stock").value(5))
                .andExpect(jsonPath("$[1].description").value("Sepatu olahraga"));
    }

    @Test
    public void testGetProductById_success() throws Exception{
        ProductDTO product = new ProductDTO(1L, "T-shirt", BigDecimal.valueOf(120000), 10, "Kaos polos");

        Mockito.when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("T-shirt"))
                .andExpect(jsonPath("$.price").value(BigDecimal.valueOf(120000)))
                .andExpect(jsonPath("$.stock").value(10))
                .andExpect(jsonPath("$.description").value("Kaos polos"));
    }

    @Test
    public void testCreateProduct_success() throws Exception{
        ProductDTO requestDTO = new ProductDTO(null, "Pant", BigDecimal.valueOf(3000), 20, "Celana kain");
        ProductDTO responseDTO = new ProductDTO(1L, "Pant", BigDecimal.valueOf(3000), 20, "Celana kain");

        Mockito.when(productService.createProduct(Mockito.any())).thenReturn(responseDTO);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pant"))
                .andExpect(jsonPath("$.stock").value(20));
    }

    @Test
    public void testUpdateProduct_success() throws Exception{
        ProductDTO requestDTO = new ProductDTO(null, "Pant", BigDecimal.valueOf(50000), 60, "Celana kain");
        ProductDTO responseDTO = new ProductDTO(1L, "Pant", BigDecimal.valueOf(50000), 60, "Celana kain");

        Mockito.when(productService.updateProduct(Mockito.eq(1L),Mockito.any(ProductDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Pant"))
                .andExpect(jsonPath("$.stock").value(60));
    }

    @Test
    public void testDeleteProduct_success() throws Exception{
        Mockito.doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }
}
