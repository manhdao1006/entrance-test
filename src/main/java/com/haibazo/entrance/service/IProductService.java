package com.haibazo.entrance.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.haibazo.entrance.dto.ProductDTO;

public interface IProductService {

    List<ProductDTO> getProducts();

    ProductDTO getProductByCode(String productCode);

    String deleteProduct(String productCode);

    ProductDTO addProduct(ProductDTO productDTO, List<Integer> colors, MultipartFile thumbnail)
            throws IOException;

    ProductDTO editProduct(String productCode, ProductDTO productDTO, List<Integer> colors, MultipartFile thumbnail)
            throws IOException;
}
