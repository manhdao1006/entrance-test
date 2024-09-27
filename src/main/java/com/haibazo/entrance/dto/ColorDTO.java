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
public class ColorDTO {

    private Integer colorId;
    private String colorName;
    private List<ProductDTO> products;
}
