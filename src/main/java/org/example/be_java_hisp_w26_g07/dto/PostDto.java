package org.example.be_java_hisp_w26_g07.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.be_java_hisp_w26_g07.entity.Product;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("post_id")
    private Integer id;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("product")
    private Product product;
    @JsonProperty("category")
    private String category;
    @JsonProperty("price")
    private Double price;

}
