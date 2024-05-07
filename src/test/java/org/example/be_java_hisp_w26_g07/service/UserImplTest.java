package org.example.be_java_hisp_w26_g07.service;


import org.example.be_java_hisp_w26_g07.repository.UserRepositoryImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserImplTest {
    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserImpl userImpl;
}