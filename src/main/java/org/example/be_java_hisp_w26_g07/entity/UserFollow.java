package org.example.be_java_hisp_w26_g07.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFollow {

    @JsonProperty("follower")
    private int follower;
    @JsonProperty("follows_to")
    private int followsTo;
    @JsonProperty("followDate")
    private LocalDate followDate;
}
