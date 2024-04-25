package org.example.be_java_hisp_w26_g07.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductsDto {
    private String categoryName;
    private Integer categoryCount;
}
