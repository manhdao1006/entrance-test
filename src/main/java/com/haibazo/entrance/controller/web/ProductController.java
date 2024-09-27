package com.haibazo.entrance.controller.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.haibazo.entrance.dto.ApiResponse;
import com.haibazo.entrance.dto.ProductDTO;
import com.haibazo.entrance.service.IProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController(value = "productAPIOfClient")
public class ProductController {

    private final IProductService productService;

    // Build API get all products
    @GetMapping("/products")
    public ApiResponse<List<ProductDTO>> getProducts() {
        return ApiResponse.<List<ProductDTO>>builder().result(productService.getProducts()).build();
    }

    // Build API get a product by product code
    @GetMapping("/product/{code}")
    public ApiResponse<ProductDTO> getProductByCode(@PathVariable String code) {
        return ApiResponse.<ProductDTO>builder().result(productService.getProductByCode(code)).build();
    }

}
