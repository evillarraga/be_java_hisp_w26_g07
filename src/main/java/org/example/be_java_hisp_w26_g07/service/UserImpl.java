package org.example.be_java_hisp_w26_g07.service;

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
}
