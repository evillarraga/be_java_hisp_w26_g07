package org.example.be_java_hisp_w26_g07.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("post_id")
    private Integer id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;
    private Product product;
    private String category;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("has_promo")
    private Boolean hasPromo;
    private Double discount;

    public Post(Integer userId, Integer id, LocalDate date, Product product, String category, Double price) {
        this.userId = userId;
        this.id = id;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
    }
}
