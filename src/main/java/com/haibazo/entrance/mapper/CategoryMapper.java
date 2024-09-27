package com.haibazo.entrance.mapper;

import org.springframework.stereotype.Component;

import com.haibazo.entrance.dto.CategoryDTO;
import com.haibazo.entrance.entity.CategoryEntity;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(entity.getCategoryId());
        categoryDTO.setCategoryName(entity.getCategoryName());

        return categoryDTO;
    }

    public CategoryEntity toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(dto.getCategoryId());
        categoryEntity.setCategoryName(dto.getCategoryName());

        return categoryEntity;
    }
}
