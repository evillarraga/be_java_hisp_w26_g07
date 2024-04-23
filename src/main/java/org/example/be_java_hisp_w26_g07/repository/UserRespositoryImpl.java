package org.example.be_java_hisp_w26_g07.repository;

import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRespository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserRespositoryImpl implements IUserRespository {

    private final List<User> users;

    public UserRespositoryImpl(@Qualifier("getUsers") List<User> users) {
        this.users = users;
    }

    @Override
    public User findById(Integer id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
