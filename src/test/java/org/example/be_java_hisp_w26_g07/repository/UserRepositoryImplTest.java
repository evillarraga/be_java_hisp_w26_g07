package org.example.be_java_hisp_w26_g07.repository;

import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.utils.GeneratorDataTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl(GeneratorDataTest.findUsers());
    }

    @Test
    @DisplayName("Encuentra usuario por su id y lo retorna correctamente")
    void findByIdTest() {
        //Arrange
        User expected = GeneratorDataTest.findUsers().get(0);

        //Act
        User output = userRepository.findById(1);

        //Assert
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("Busca un usuario por id pero no existe y retorna null")
    void findByIdNullTest() {
        //Act
        User output = userRepository.findById(20);

        //Assert
        assertNull(output);
    }
}