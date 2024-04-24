package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.dto.SuccessResponseDto;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestExecption;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRespository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements IUserService {
    private final IUserRespository iUserRespository;

    public UserImpl(@Autowired IUserRespository iUserRespository) {
        this.iUserRespository = iUserRespository;
    }

    @Override
    public SuccessResponseDto unfollow(Integer userId, Integer userIdToUnfollow) {
        User foundUser = iUserRespository.findById(userId);
        if (foundUser == null){
            throw new NotFoundException("El usuario no fue encontrado");
        }
        boolean followDeleted = iUserRespository.unfollow(foundUser, userIdToUnfollow);
        if (!followDeleted) {
            throw new BadRequestExecption("No se encontró el usuario para dejar de seguir");
        }
        return new SuccessResponseDto("Se ha dejado de seguir al usuario");
    }
}
