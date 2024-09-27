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
import com.haibazo.entrance.entity.StyleEntity;
import com.haibazo.entrance.exception.ResourceExistedException;
import com.haibazo.entrance.exception.ResourceNotFormatException;
import com.haibazo.entrance.exception.ResourceNotFoundException;
import com.haibazo.entrance.mapper.ProductMapper;
import com.haibazo.entrance.repository.ColorRepository;
import com.haibazo.entrance.repository.ProductRepository;
import com.haibazo.entrance.repository.StyleRepository;
import com.haibazo.entrance.service.IProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ColorRepository colorRepository;
    private final StyleRepository styleRepository;

    @Value("${product.images.path}")
    private String imagePath;

    /*
     * get all products
     * 
     * @return products
     */
    @Override
    public List<ProductDTO> getProducts() {
        List<ProductEntity> entities = productRepository.findAllByDelFlag("1");
        return entities.stream().map(productMapper::toDTO).toList();
    }

    /*
     * get product by product code
     * 
     * @param product code
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
     * delete product
     * 
     * @param product code
     * 
     * @return "ok"
     */
    @Transactional
    @Override
    public String deleteProduct(String productCode) {
        ProductEntity entity = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product with code: " + productCode + " not found"));

        if (entity.getThumbnail() != null && !entity.getThumbnail().isEmpty()) {
            try {
                Path path = Paths.get(imagePath, entity.getThumbnail());
                Files.deleteIfExists(path);
            } catch (IOException e) {
                throw new ResourceNotFormatException("Error deleting file: " + e.getMessage());
            }
        }
        productRepository.deleteByProductCode(productCode);

        return "ok";
    }

    /*
     * soft delete product
     * 
     * @param product code
     * 
     * @return "ok"
     */
    @Transactional
    @Override
    public String softDeleteProduct(String productCode) {
        ProductEntity entity = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException("Product with code: " + productCode + " not found"));

        if (entity != null) {
            entity.setDelFlag("0");
        }

        return "ok";
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

        checkExistedProduct(productDTO.getProductCode());
        ProductEntity productEntity = productMapper.toEntity(productDTO);

        // set thumbnail
        if (thumbnail != null && !thumbnail.isEmpty()) {
            validateImage(thumbnail);
            String fileName = saveImageToFolder(thumbnail);
            productEntity.setThumbnail(fileName);
        }

        // add colors
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

    /*
     * edit existed product
     * 
     * @param existed product, colors id, thumbnail
     * 
     * @return product
     */
    @Transactional
    @Override
    public ProductDTO editProduct(String productCode, ProductDTO productDTO, List<Integer> colorIds,
            MultipartFile thumbnail)
            throws IOException {
        ProductEntity productEntity = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with code: " + productCode + " is not found"));

        productEntity.setProductName(productDTO.getProductName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setSize(productDTO.getSize());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setRate(productDTO.getRate());

        if (productDTO.getStyle() != null && productDTO.getStyle().getStyleId() != null) {
            StyleEntity styleEntity = styleRepository.findById(productDTO.getStyle().getStyleId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Style with id " + productDTO.getStyle().getStyleId() + " is not found"));
            productEntity.setStyle(styleEntity);
        }

        // set thumbnail
        if (thumbnail != null && !thumbnail.isEmpty()) {
            // delete old image
            Path path = Paths.get(imagePath, productEntity.getThumbnail());
            Files.deleteIfExists(path);

            validateImage(thumbnail);
            String fileName = saveImageToFolder(thumbnail);
            productEntity.setThumbnail(fileName);
        }

        // add colors
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

        ProductEntity updatedProduct = productRepository.save(productEntity);
        return productMapper.toDTO(updatedProduct);
    }

    // save image to folder
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

    // validate image
    @SuppressWarnings("null")
    private void validateImage(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        if (!contentType.startsWith("image/")) {
            throw new ResourceNotFormatException("Uploaded file is not an image");
        }
    }

    // check existed product
    private void checkExistedProduct(String productCode) {
        if (productRepository.existsByProductCode(productCode)) {
            throw new ResourceExistedException("Product with ID " + productCode + " is existed!");
        }
    }

}
