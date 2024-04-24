package org.example.be_java_hisp_w26_g07.repository.interfaces;

import org.example.be_java_hisp_w26_g07.dto.FollowedResponseDto;
import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.entity.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();

    User findById(Integer id);

    Boolean addFollowerById(Integer id, Integer userToFollow);

    Boolean userFollowSeller(Integer id, Integer userToFollow);

    List<Post> findProductByFollow(User user);

}
