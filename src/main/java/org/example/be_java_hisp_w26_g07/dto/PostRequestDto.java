package org.example.be_java_hisp_w26_g07.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.be_java_hisp_w26_g07.entity.Product;

import java.time.LocalDate;

public class PostRequestDto {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("product")
    private Product product;
    @JsonProperty("category")
    private String category;
    @JsonProperty("price")
    private double price;

}
