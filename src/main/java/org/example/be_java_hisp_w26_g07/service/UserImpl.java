package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.dto.*;
import org.example.be_java_hisp_w26_g07.dto.users.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.dto.users.FollowedResponseDto;
import org.example.be_java_hisp_w26_g07.dto.users.FollowersResponseDto;
import org.example.be_java_hisp_w26_g07.dto.users.UserInfoFollowsDto;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.exception.NotAcceptable;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.example.be_java_hisp_w26_g07.utils.UserMessageError;
import org.example.be_java_hisp_w26_g07.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserImpl implements IUserService {
    private final IUserRepository iUserRepository;

    public UserImpl(@Autowired IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    private List<UserInfoFollowsDto> getUserInfoFollowers(List<Integer> usersId) {
        List<UserInfoFollowsDto> followers = new ArrayList<>();
        for (Integer userIdToFind : usersId) {
            User follower = iUserRepository.findById(userIdToFind);
            followers.add(new UserInfoFollowsDto(follower.getId(), follower.getName()));
        }
        return followers;
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
        if (userId.equals(sellerId))
            throw new BadRequestException(UserMessageError.ID_CLIENT_SELLER_IS_EQUALS.getMessage());
        if (follower == null) throw new BadRequestException(UserMessageError.CLIENT_NOT_FOUND.getMessage(userId));
        if (seller == null) throw new BadRequestException(UserMessageError.SELLER_NOT_FOUND.getMessage(sellerId));
        if (!seller.getIsSeller()) throw new BadRequestException(UserMessageError.CLIENT_IS_NOT_SELLER.getMessage());

        if (iUserRepository.userFollowSeller(userId, sellerId)) throw new BadRequestException("El usuario ya se encuentra siguendo a este vendedor");
        iUserRepository.addFollowerById(userId, sellerId);
        return true;
    }

    @Override
    public FollowedResponseDto findFollowedUsers(Integer id, String order) {
        User user = iUserRepository.findById(id);
        if (user == null) throw new NotFoundException(UserMessageError.SELLER_NOT_FOUND.getMessage(id));
        List<Integer> followedIdList = iUserRepository.followedIdByUserId(id);
        List<UserInfoFollowsDto> userInfoFollowsDtos = followedIdList
                .stream()
                .map(followedId -> {
                    User current = iUserRepository.findById(followedId);
                    return new UserInfoFollowsDto(current.getId(), current.getName());
                }).toList();
        List<UserInfoFollowsDto> orderedList = UserUtils.getUserInfoFollowsDtoByOrder(userInfoFollowsDtos, order);
        return new FollowedResponseDto(id, user.getName(), orderedList);
    }

    @Override
    public FollowersResponseDto findFollowersByOrder(Integer userId, String order) {
        User seller = iUserRepository.findById(userId);
        if (seller == null) throw new NotFoundException(UserMessageError.SELLER_NOT_FOUND.getMessage(userId));
        if (!seller.getIsSeller()) throw new BadRequestException(UserMessageError.CLIENT_IS_NOT_SELLER.getMessage());
        List<Integer> followerIdList = iUserRepository.followerIdBySellerId(userId);
        List<UserInfoFollowsDto> userInfoFollowsDto = getUserInfoFollowers(followerIdList);
        return new FollowersResponseDto(
                seller.getId(),
                seller.getName(),
                UserUtils.getUserInfoFollowsDtoByOrder(userInfoFollowsDto, order)
        );
    }

    @Override
    public CountFollowersResponseDto getNumberOfSellersFollowed(Integer userId) {
        User user = iUserRepository.findById(userId);
        if (user == null) throw new NotFoundException(UserMessageError.CLIENT_NOT_FOUND.getMessage(userId));
        if (!user.getIsSeller()) throw new NotAcceptable(UserMessageError.CLIENT_IS_NOT_SELLER.getMessage());
        List<Integer> followerIdList = iUserRepository.followerIdBySellerId(userId);
        int followerCount = followerIdList.size();
        return new CountFollowersResponseDto(user.getId(), user.getName(), followerCount);
    }

    @Override
    public SuccessResponseDto unfollow(Integer userId, Integer userIdToUnfollow) {
        User followerUser = iUserRepository.findById(userId);
        User sellerUser = iUserRepository.findById(userIdToUnfollow);
        if (followerUser == null) throw new BadRequestException(UserMessageError.CLIENT_NOT_FOUND.getMessage(userId));
        if (sellerUser == null) {
            throw new BadRequestException(UserMessageError.SELLER_NOT_FOUND.getMessage(userIdToUnfollow));
        }
        boolean followDeleted = iUserRepository.unfollow(followerUser, sellerUser);
        if (!followDeleted) {
            throw new BadRequestException("No se pudo completar la acción");
        }
        return new SuccessResponseDto("Se ha dejado de seguir al usuario");
    }
}
