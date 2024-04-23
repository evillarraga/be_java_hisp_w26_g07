package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
