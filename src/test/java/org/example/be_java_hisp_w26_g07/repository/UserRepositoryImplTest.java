package org.example.be_java_hisp_w26_g07.repository;

import org.example.be_java_hisp_w26_g07.utils.GeneratorDataTest;
import org.junit.jupiter.api.BeforeEach;

class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl(GeneratorDataTest.findUsers());
    }
}