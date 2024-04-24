package org.example.be_java_hisp_w26_g07.service.interfaces;

import org.example.be_java_hisp_w26_g07.dto.CountFollowersResponseDto;

import java.util.List;

public interface IUserService {

    public CountFollowersResponseDto getNumberOfSellersFollowed(String userId);

}
