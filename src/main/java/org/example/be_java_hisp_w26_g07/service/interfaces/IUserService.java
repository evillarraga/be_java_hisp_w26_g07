package org.example.be_java_hisp_w26_g07.service.interfaces;

import org.example.be_java_hisp_w26_g07.dto.SuccessResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IUserService {
    public SuccessResponseDto unfollow(Integer userId, Integer userIdToUnfollow);
}
