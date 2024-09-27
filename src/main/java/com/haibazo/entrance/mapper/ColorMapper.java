package com.haibazo.entrance.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.haibazo.entrance.dto.ColorDTO;
import com.haibazo.entrance.entity.ColorEntity;

@Component
public class ColorMapper {

    public ColorDTO toDTO(ColorEntity entity) {
        if (entity == null) {
            return null;
        }

        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setColorId(entity.getColorId());
        colorDTO.setColorName(entity.getColorName());

        return colorDTO;
    }

    public ColorEntity toEntity(ColorDTO dto) {
        if (dto == null) {
            return null;
        }

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setColorId(dto.getColorId());
        colorEntity.setColorName(dto.getColorName());

        return colorEntity;
    }

    public List<ColorDTO> toDTOs(List<ColorEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toDTO).toList();
    }

    public List<ColorEntity> toEntities(List<ColorDTO> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toEntity).toList();
    }
}
