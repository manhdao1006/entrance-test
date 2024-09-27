package com.haibazo.entrance.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.haibazo.entrance.dto.ProductDTO;
import com.haibazo.entrance.entity.ColorEntity;
import com.haibazo.entrance.entity.ProductEntity;
import com.haibazo.entrance.exception.ResourceExistedException;
import com.haibazo.entrance.exception.ResourceNotFormatException;
import com.haibazo.entrance.exception.ResourceNotFoundException;
import com.haibazo.entrance.mapper.ProductMapper;
import com.haibazo.entrance.repository.ColorRepository;
import com.haibazo.entrance.repository.ProductRepository;
import com.haibazo.entrance.service.IProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ColorRepository colorRepository;

    @Value("${product.images.path}")
    private String imagePath;

    /*
     * get all products
     * 
     * @return products
     */
    @Override
    public List<ProductDTO> getProducts() {
        List<ProductEntity> entities = productRepository.findAll();
        return entities.stream().map(productMapper::toDTO).toList();
    }

    /*
     * get product by product code
     * 
     * @param productCode
     * 
     * @return product
     */
    @Override
    public ProductDTO getProductByCode(String productCode) {
        ProductEntity entity = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product with code: " + productCode + " not found"));
        ProductDTO model = productMapper.toDTO(entity);
        return model;
    }

    /*
     * create new product
     * 
     * @param new product, colors id, thumbnail
     * 
     * @return product
     */
    @Transactional
    @Override
    public ProductDTO addProduct(ProductDTO productDTO, List<Integer> colorIds, MultipartFile thumbnail)
            throws IOException {

        // check existed product
        if (productRepository.existsByProductCode(productDTO.getProductCode())) {
            throw new ResourceExistedException("Product with ID " + productDTO.getProductCode() + " is existed!");
        }
        ProductEntity productEntity = productMapper.toEntity(productDTO);

        // handle set thumbnail
        if (thumbnail != null && !thumbnail.isEmpty()) {
            validateImage(thumbnail);
            String fileName = saveImageToFolder(thumbnail);
            productEntity.setThumbnail(fileName);
        }

        // handle add colors
        if (colorIds != null && !colorIds.isEmpty()) {
            List<ColorEntity> colorEntities = new ArrayList<>();
            for (Integer colorId : colorIds) {
                ColorEntity colorEntity = colorRepository.findById(colorId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Color with ID: " + colorId + " not found"));
                colorEntities.add(colorEntity);
            }
            productEntity.setColors(colorEntities);
        } else {
            productEntity.setColors(new ArrayList<>());
        }

        ProductEntity savedProduct = productRepository.save(productEntity);

        return productMapper.toDTO(savedProduct);
    }

    // handle save image to folder
    private String saveImageToFolder(MultipartFile imageFile) throws IOException {
        try {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path path = Paths.get(imagePath, fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, imageFile.getBytes());
            return fileName;
        } catch (IOException e) {
            throw new ResourceNotFormatException("Error saving file: " + e.getMessage());
        }
    }

    // handle validate image
    @SuppressWarnings("null")
    private void validateImage(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        if (!contentType.startsWith("image/")) {
            throw new ResourceNotFormatException("Uploaded file is not an image");
        }
    }

}
