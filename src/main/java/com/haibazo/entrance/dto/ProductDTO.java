package com.haibazo.entrance.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Integer productId;
    private String productCode;
    private String productName;
    private String description;
    private BigDecimal price;
    private String size;
    private Integer quantity;
    private BigDecimal rate;
    private String thumbnail;
    private String delFlag;
    private StyleDTO style;
    private List<ImageDTO> images;
    private List<ColorDTO> colors;
}
