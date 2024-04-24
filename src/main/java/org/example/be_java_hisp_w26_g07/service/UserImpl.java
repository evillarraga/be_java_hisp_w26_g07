package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.dto.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestExecption;
import org.example.be_java_hisp_w26_g07.exception.NotAcceptable;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRespository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements IUserService {
    private final IUserRespository iUserRespository;

    public UserImpl(@Autowired IUserRespository iUserRespository) {
        this.iUserRespository = iUserRespository;
    }

    @Override
    public CountFollowersResponseDto getNumberOfSellersFollowed(String userId) {
        if (!userId.matches("\\d+")) {
            throw new BadRequestExecption("El valor ingresado no es numérico");
        }
        User user = iUserRespository.findById(Integer.parseInt(userId));
        if (user == null) {
            throw new NotFoundException("Usuario no encontrado");
        }
        if (!user.getIsSeller()) {
            throw new NotAcceptable("Existe el usuario pero no es vendedor");
        }
        return new CountFollowersResponseDto(user.getId(), user.getName(), user.getFollowerIds().size());
    }
}
