package org.example.be_java_hisp_w26_g07.service.interfaces;

import org.example.be_java_hisp_w26_g07.dto.FollowedResponseDto;

import java.util.List;

import org.example.be_java_hisp_w26_g07.dto.CountFollowersResponseDto;

public interface IUserService {
    Boolean userFollowSeller(Integer userId, Integer sellerId);

    FollowedResponseDto findFollowedUsers(Integer id, String order);


    public CountFollowersResponseDto getNumberOfSellersFollowed(String userId);

}
