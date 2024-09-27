package com.haibazo.entrance.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.haibazo.entrance.dto.ColorDTO;
import com.haibazo.entrance.dto.ImageDTO;
import com.haibazo.entrance.dto.ProductDTO;
import com.haibazo.entrance.dto.StyleDTO;
import com.haibazo.entrance.entity.ColorEntity;
import com.haibazo.entrance.entity.ImageEntity;
import com.haibazo.entrance.entity.ProductEntity;
import com.haibazo.entrance.entity.StyleEntity;

@Component
public class ProductMapper {

    private final StyleMapper styleMapper;
    private final ImageMapper imageMapper;
    private final ColorMapper colorMapper;

    public ProductMapper(@Lazy StyleMapper styleMapper, @Lazy ImageMapper imageMapper, @Lazy ColorMapper colorMapper) {
        this.styleMapper = styleMapper;
        this.imageMapper = imageMapper;
        this.colorMapper = colorMapper;
    }

    public ProductDTO toDTO(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(entity.getProductId());
        productDTO.setProductCode(entity.getProductCode());
        productDTO.setProductName(entity.getProductName());
        productDTO.setDescription(entity.getDescription());
        productDTO.setPrice(entity.getPrice());
        productDTO.setSize(entity.getSize());
        productDTO.setQuantity(entity.getQuantity());
        productDTO.setRate(entity.getRate());
        productDTO.setThumbnail(entity.getThumbnail());
        productDTO.setDelFlag("1");
        productDTO.setStyle(toStyleDTO(entity.getStyle()));
        productDTO.setImages(toImagesDTO(entity.getImages()));
        productDTO.setColors(toColorsDTO(entity.getColors()));

        return productDTO;
    }

    public ProductEntity toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCode(dto.getProductCode());
        productEntity.setProductName(dto.getProductName());
        productEntity.setDescription(dto.getDescription());
        productEntity.setPrice(dto.getPrice());
        productEntity.setSize(dto.getSize());
        productEntity.setQuantity(dto.getQuantity());
        productEntity.setRate(dto.getRate());
        productEntity.setThumbnail(dto.getThumbnail());
        productEntity.setStyle(toStyleEntity(dto.getStyle()));
        productEntity.setImages(toImagesEntity(dto.getImages()));
        productEntity.setColors(toColorsEntity(dto.getColors()));

        return productEntity;
    }

    public List<ProductDTO> toDTOs(List<ProductEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toDTO).toList();
    }

    public List<ProductEntity> toEntities(List<ProductDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toEntity).toList();
    }

    private StyleDTO toStyleDTO(StyleEntity style) {
        return styleMapper.toDTO(style);
    }

    private List<ImageDTO> toImagesDTO(List<ImageEntity> images) {
        return imageMapper.toDTOs(images);
    }

    private List<ColorDTO> toColorsDTO(List<ColorEntity> colors) {
        return colorMapper.toDTOs(colors);
    }

    private StyleEntity toStyleEntity(StyleDTO style) {
        return styleMapper.toEntity(style);
    }

    private List<ImageEntity> toImagesEntity(List<ImageDTO> images) {
        return imageMapper.toEntities(images);
    }

    private List<ColorEntity> toColorsEntity(List<ColorDTO> colors) {
        return colorMapper.toEntities(colors);
    }
}
