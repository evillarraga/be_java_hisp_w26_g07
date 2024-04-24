package org.example.be_java_hisp_w26_g07.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountFollowersResponseDto implements Serializable {

    @JsonProperty("user_id")
    private Integer id;
    @JsonProperty("user_name")
    private String name;
    @JsonProperty("followers_count")
    private Integer followersCount;

}
