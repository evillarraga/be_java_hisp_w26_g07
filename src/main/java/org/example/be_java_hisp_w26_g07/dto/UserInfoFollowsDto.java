package org.example.be_java_hisp_w26_g07.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoFollowsDto {
    @JsonProperty("user_id")
    private int id;
    @JsonProperty("user_name")
    private String name;
}