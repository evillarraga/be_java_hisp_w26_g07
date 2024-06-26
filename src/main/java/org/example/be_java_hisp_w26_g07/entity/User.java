package org.example.be_java_hisp_w26_g07.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @JsonProperty("user_id")
    private Integer id;
    @JsonProperty("user_name")
    private String name;
    @JsonProperty("posts")
    private List<Post> posts;
    @JsonProperty("followerIds")
    private List<Integer> followerIds;
    @JsonProperty("followedIds")
    private List<Integer> followedIds;
    @JsonProperty("isSeller")
    private Boolean isSeller;
}
