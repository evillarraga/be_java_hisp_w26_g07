package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.dto.FollowedResponseDto;
import org.example.be_java_hisp_w26_g07.dto.UserInfoFollowsDto;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserImpl implements IUserService {
    private final IUserRepository iUserRepository;

    public UserImpl(@Autowired IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    /**
     * funcion usada para que un usuario pueda seguir a un vendedor
     *
     * @param userId   usuario
     * @param sellerId vendedor a seguir
     */
    @Override
    public Boolean userFollowSeller(Integer userId, Integer sellerId) {
        User seller = iUserRepository.findById(sellerId);
        User follower = iUserRepository.findById(userId);
        if (userId.equals(sellerId)) {
            throw new BadRequestException("El id del usuario no puede ser igual al vendedor");
        }
        if (seller == null || follower == null) {
            throw new BadRequestException("El usuario o vendedor no existe");
        }
        if (!seller.getIsSeller()) {
            throw new BadRequestException("El usuario a seguir con el id " + sellerId + " no es vendedor");
        }
        if (iUserRepository.userFollowSeller(userId, sellerId)) {
            return false;
        }
        iUserRepository.addFollowerById(userId, sellerId);
        return true;
    }

    @Override
    public FollowedResponseDto findFollowedUsers(Integer id, String order) {
        User user = iUserRepository.findById(id);
        Stream<UserInfoFollowsDto> userInfoFollowsDtos = user.getFollowedIds().stream()
                .map(followedId -> iUserRepository.findById(followedId))
                .map(followedUser -> new UserInfoFollowsDto(followedUser.getId(), followedUser.getName()))
                ;
        List<UserInfoFollowsDto> list;
        if(order.equals("name_desc")){
            list = userInfoFollowsDtos.sorted(Comparator.comparing(UserInfoFollowsDto::getName).reversed())
                    .collect(Collectors.toList());

        }else {
            list = userInfoFollowsDtos.sorted(Comparator.comparing(UserInfoFollowsDto::getName))
                    .collect(Collectors.toList());
        }


        return new FollowedResponseDto(id, user.getName(), list );

    }
}
