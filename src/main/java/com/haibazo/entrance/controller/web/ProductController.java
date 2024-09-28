package com.haibazo.entrance.controller.web;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Build API get products by price between
    @GetMapping("/products/price")
    public ApiResponse<List<ProductDTO>> getProductsByPriceBetween(@RequestParam("from") BigDecimal fromPrice,
            @RequestParam("to") BigDecimal toPrice) {
        return ApiResponse.<List<ProductDTO>>builder()
                .result(productService.getProductsByPriceBetween(fromPrice, toPrice))
                .build();
    }

    // Build API get products by color or category or style
    @GetMapping("/products/filter")
    public ApiResponse<List<ProductDTO>> getProductsByKeyword(
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "style", required = false) String style) {
        return ApiResponse.<List<ProductDTO>>builder()
                .result(productService.getProductsByKeyword(color, category, style))
                .build();
    }

    // Build API sort product by price from low to high
    @GetMapping("/products/price-asc")
    public ApiResponse<List<ProductDTO>> sortedByPriceAsc() {
        return ApiResponse.<List<ProductDTO>>builder()
                .result(productService.sortedByPriceAsc())
                .build();
    }

    // Build API sort product by price from high to low
    @GetMapping("/products/price-desc")
    public ApiResponse<List<ProductDTO>> sortedByPriceDesc() {
        return ApiResponse.<List<ProductDTO>>builder()
                .result(productService.sortedByPriceDesc())
                .build();
    }

    // Build API get all products
    @GetMapping("/products")
    public ApiResponse<List<ProductDTO>> getProducts() {
        return ApiResponse.<List<ProductDTO>>builder()
                .result(productService.getProducts())
                .build();
    }

    // Build API get a product by product code
    @GetMapping("/product/{code}")
    public ApiResponse<ProductDTO> getProductByCode(@PathVariable String code) {
        return ApiResponse.<ProductDTO>builder()
                .result(productService.getProductByCode(code))
                .build();
    }

}
