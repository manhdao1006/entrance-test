package com.haibazo.entrance.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleDTO {

    private Integer styleId;
    private String styleName;
    private CategoryDTO category;
    private List<ProductDTO> products;
}
