package org.example.be_java_hisp_w26_g07.controller;

import org.example.be_java_hisp_w26_g07.dto.users.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.service.interfaces.IUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    IUserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void follow() {
    }

    @Test
    @DisplayName("Verifica el controlador que devuelva un ResponseEntity con Status Code OK")
    void numberOfSellersFollowedTest() {
        //Arrange
        //Creación de usuario y id´s de seguidores que pertenecen al vendedor
        Integer userId = 1;
        User userMock = new User(userId, "Juan", true);
        CountFollowersResponseDto countFollowersResponseDto = new CountFollowersResponseDto(userId, userMock.getName(),
                5);
        ResponseEntity<CountFollowersResponseDto> expected = new ResponseEntity<>(
                countFollowersResponseDto, HttpStatus.OK);

        //Act
        when(userService.getNumberOfSellersFollowed(userId)).thenReturn(countFollowersResponseDto);

        ResponseEntity<CountFollowersResponseDto> output = userController.numberOfSellersFollowed(userId);

        //Assert
        assertEquals(expected, output);
        assertEquals(HttpStatus.OK, output.getStatusCode());
    }

    @Test
    void listOfFollowersSellesrs() {
    }

    @Test
    void followedList() {
    }

    @Test
    void unfollow() {
    }
}