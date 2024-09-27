package com.haibazo.entrance.controller.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.haibazo.entrance.dto.ApiResponse;
import com.haibazo.entrance.dto.CategoryDTO;
import com.haibazo.entrance.dto.ProductDTO;
import com.haibazo.entrance.dto.StyleDTO;
import com.haibazo.entrance.service.IProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController(value = "productAPIOfAdmin")
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService productService;

    @PostMapping("/create")
    public ApiResponse<ProductDTO> addProduct(
            @RequestParam(value = "productCode", required = true) String productCode,
            @RequestParam(value = "productName", required = true) String productName,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) BigDecimal price,
            @RequestParam(value = "size", required = false) String size,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            @RequestParam(value = "rate", required = false) BigDecimal rate,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            @RequestParam(value = "styleId", required = false) Integer styleId,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "colors", required = false) List<Integer> colors) throws IOException {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductCode(productCode);
        productDTO.setProductName(productName);
        productDTO.setDescription(description);
        productDTO.setPrice(price);
        productDTO.setSize(size);
        productDTO.setQuantity(quantity);
        productDTO.setRate(rate);

        if (styleId != null) {
            StyleDTO styleDTO = new StyleDTO();
            styleDTO.setStyleId(styleId);
            productDTO.setStyle(styleDTO);
        }

        if (categoryId != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategoryId(categoryId);
            if (productDTO.getStyle() != null) {
                productDTO.getStyle().setCategory(categoryDTO);
            }
        }

        ProductDTO createdProduct = productService.addProduct(productDTO, colors, thumbnail);

        return ApiResponse.<ProductDTO>builder().result(createdProduct).build();
    }
}
