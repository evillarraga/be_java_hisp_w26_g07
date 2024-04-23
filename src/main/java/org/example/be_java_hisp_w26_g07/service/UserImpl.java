package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestExeception;
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

    /**
     * funcion usada para que un usuario pueda seguir a un vendedor
     *
     * @param userId   usuario
     * @param sellerId vendedor a seguir
     */
    @Override
    public Boolean userFollowSeller(Integer userId, Integer sellerId) {
        User seller = iUserRespository.findById(sellerId);
        User follower = iUserRespository.findById(userId);
        if (userId.equals(sellerId)) {
            throw new BadRequestExeception("El id del usuario no puede ser igual al vendedor");
        }
        if (seller == null || follower == null) {
            throw new BadRequestExeception("El usuario o vendedor no existe");
        }
        if (!seller.getIsSeller()) {
            throw new BadRequestExeception("El usuario a seguir con el id " + sellerId + " no es vendedor");
        }
        if (iUserRespository.userFollowSeller(userId, sellerId)) {
            return false;
        }
        iUserRespository.addFollowerById(userId, sellerId);
        return true;
    }
}
