package com.haibazo.entrance.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.haibazo.entrance.dto.ImageDTO;
import com.haibazo.entrance.entity.ImageEntity;

@Component
public class ImageMapper {

    public ImageDTO toDTO(ImageEntity entity) {
        if (entity == null) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImageId(entity.getImageId());
        imageDTO.setImageName(entity.getImageName());

        return imageDTO;
    }

    public ImageEntity toEntity(ImageDTO dto) {
        if (dto == null) {
            return null;
        }

        ImageEntity entity = new ImageEntity();
        entity.setImageId(dto.getImageId());
        entity.setImageName(dto.getImageName());

        return entity;
    }

    public List<ImageDTO> toDTOs(List<ImageEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toDTO).toList();
    }

    public List<ImageEntity> toEntities(List<ImageDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toEntity).toList();
    }

}
