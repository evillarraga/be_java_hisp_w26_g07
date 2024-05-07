package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.dto.users.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.NotAcceptable;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserImplTest {

    @Mock
    IUserRepository iUserRepository;

    @InjectMocks
    UserImpl userService;

    @Test
    void userFollowSeller() {
    }

    @Test
    void findFollowedUsers() {
    }

    @Test
    void findFollowersByOrder() {
    }

    @Test
    @DisplayName("T-0007 Verificar que la cantidad de seguidores de un determinado usuario sea correcta")
    void getNumberOfSellersFollowedTest() {
        //Arrange
        //Creación de usuario y id´s de seguidores que pertenecen al vendedor
        Integer userId = 1;
        User userMock = new User(userId, "Juan", true);
        List<Integer> followersIds = List.of(5,6,7,8,9);

        CountFollowersResponseDto expected = new CountFollowersResponseDto(userId, userMock.getName(), 5);

        //Act
        when(iUserRepository.findById(userId)).thenReturn(userMock);
        when(iUserRepository.followerIdBySellerId(userId)).thenReturn(followersIds);

        CountFollowersResponseDto output = userService.getNumberOfSellersFollowed(userId);

        //Assert
        assertEquals(expected, output);
    }

    @Test
    @DisplayName("T-0007 Verifica si el usuario no existe retorna la excepción NotFoundException")
    void getNumberOfSellersFollowedNotFoundExceptionTest() {
        //Arrange
        Integer userId = 1;

        //Act and Assert
        when(iUserRepository.findById(userId)).thenReturn(null);
        assertThrows(NotFoundException.class,()->userService.getNumberOfSellersFollowed(userId));
    }

    @Test
    @DisplayName("T-0007 Verifica si el usuario no es vendedor retorna la excepción NotAcceptable")
    void getNumberOfSellersFollowedNotAcceptableTest() {
        //Arrange
        //Creación de usuario que no es vendedor, propiedad isseller false
        Integer userId = 1;
        User userMock = new User(userId, "Juan", false);

        //Act and Assert
        when(iUserRepository.findById(userId)).thenReturn(userMock);
        assertThrows(NotAcceptable.class,()->userService.getNumberOfSellersFollowed(userId));
    }

    @Test
    void unfollow() {
    }
}