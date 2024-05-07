package org.example.be_java_hisp_w26_g07.service;


import org.example.be_java_hisp_w26_g07.dto.SuccessResponseDto;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.dto.users.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.exception.NotAcceptable;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.UserRepositoryImpl;

import org.example.be_java_hisp_w26_g07.utils.GeneratorDataTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserImplTest {
    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserImpl userImpl;

    @Test
    @DisplayName("T-0007 Verificar que la cantidad de seguidores de un determinado usuario sea correcta")
    void getNumberOfSellersFollowedTest() {
        //Arrange
        //Creación de usuario y id´s de seguidores que pertenecen al vendedor
        Integer userId = 1;
        User userMock = GeneratorDataTest.findUsers().get(0);

        CountFollowersResponseDto expected = new CountFollowersResponseDto(userId, userMock.getName(),
                userMock.getFollowerIds().size());

        //Act
        when(userRepository.findById(userId)).thenReturn(userMock);

        CountFollowersResponseDto output = userImpl.getNumberOfSellersFollowed(userId);

        //Assert
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("T-0007 Verifica si el usuario no existe retorna la excepción NotFoundException")
    void getNumberOfSellersFollowedNotFoundExceptionTest() {
        //Arrange
        Integer userId = 1;

        //Act and Assert
        when(userRepository.findById(userId)).thenReturn(null);
        assertThrows(NotFoundException.class,()->userImpl.getNumberOfSellersFollowed(userId));
    }

    @Test
    @DisplayName("T-0007 Verifica si el usuario no es vendedor retorna la excepción NotAcceptable")
    void getNumberOfSellersFollowedNotAcceptableTest() {
        //Arrange
        //Creación de usuario que no es vendedor, propiedad isseller false
        Integer userId = 1;
        User userMock = new User(userId, "Juan", List.of(),List.of(),List.of(),false);

        //Act and Assert
        when(userRepository.findById(userId)).thenReturn(userMock);
        assertThrows(NotAcceptable.class,()->userImpl.getNumberOfSellersFollowed(userId));
    }

    @Test
    @DisplayName("T-0002 unfollow user: successful")
    public void unfollowUserSuccessful() {
        // Arrange
        Integer clientId = 1;
        Integer sellerId = 2;
        List<User> users = GeneratorDataTest.usersById(clientId, sellerId);
        SuccessResponseDto expected = new SuccessResponseDto("Se ha dejado de seguir al usuario");

        // Act
        when(userRepository.unfollow(users.get(0), users.get(1))).thenReturn(true);
        initializeMockUserRepository(users);
        SuccessResponseDto actual = userImpl.unfollow(clientId, sellerId);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("T-0002 unfollow user: failed (client not follow seller)")
    public void unfollowUserFailed() {
        // Arrange
        Integer clientId = 1;
        Integer sellerId = 5;
        List<User> users = GeneratorDataTest.usersById(clientId, sellerId);

        // Act
        when(userRepository.unfollow(users.get(0), users.get(1))).thenReturn(false);
        initializeMockUserRepository(users);
        Assertions.assertThrows(BadRequestException.class, () -> userImpl.unfollow(clientId, sellerId));
    }

    @Test
    @DisplayName("T-0002 unfollow user: failed (user not found)")
    public void unfollowUserNotFound() {
        // Arrange
        Integer clientId = 100;
        Integer sellerId = 5;
        List<User> users = GeneratorDataTest.usersById(sellerId);

        // Act
        when(userRepository.findById(clientId)).thenReturn(null);
        initializeMockUserRepository(users);
        Assertions.assertThrows(BadRequestException.class, () -> userImpl.unfollow(clientId, sellerId));
    }

    @Test
    @DisplayName("T-0002 unfollow user: failed (seller not found)")
    public void unfollowSellerNotFound() {
        // Arrange
        Integer clientId = 1;
        Integer sellerId = 500;
        List<User> users = GeneratorDataTest.usersById(clientId);

        // Act
        when(userRepository.findById(sellerId)).thenReturn(null);
        initializeMockUserRepository(users);
        Assertions.assertThrows(BadRequestException.class, () -> userImpl.unfollow(clientId, sellerId));
    }

    public void initializeMockUserRepository(List<User> users) {
        for (User user : users) {
            when(userRepository.findById(user.getId())).thenReturn(user);
        }
    }
}