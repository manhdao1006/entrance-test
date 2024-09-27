package com.haibazo.entrance.mapper;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.haibazo.entrance.dto.CategoryDTO;
import com.haibazo.entrance.dto.StyleDTO;
import com.haibazo.entrance.entity.CategoryEntity;
import com.haibazo.entrance.entity.StyleEntity;

@Component
public class StyleMapper {

    private final CategoryMapper categoryMapper;

    public StyleMapper(@Lazy CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public StyleDTO toDTO(StyleEntity entity) {
        if (entity == null) {
            return null;
        }

        StyleDTO styleDTO = new StyleDTO();
        styleDTO.setStyleId(entity.getStyleId() != null ? entity.getStyleId() : null);
        styleDTO.setStyleName(entity.getStyleName());
        styleDTO.setCategory(toCategoryDTO(entity.getCategory()));

        return styleDTO;
    }

    public StyleEntity toEntity(StyleDTO dto) {
        if (dto == null) {
            return null;
        }

        StyleEntity styleEntity = new StyleEntity();
        styleEntity.setStyleId(dto.getStyleId());
        styleEntity.setStyleName(dto.getStyleName());
        styleEntity.setCategory(toCategoryEntity(dto.getCategory()));

        return styleEntity;
    }

    private CategoryDTO toCategoryDTO(CategoryEntity category) {
        return categoryMapper.toDTO(category);
    }

    private CategoryEntity toCategoryEntity(CategoryDTO category) {
        return categoryMapper.toEntity(category);
    }
}
