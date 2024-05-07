package org.example.be_java_hisp_w26_g07.service;


import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.dto.users.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.exception.NotAcceptable;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.repository.UserRepositoryImpl;
import org.example.be_java_hisp_w26_g07.utils.GeneratorDataTest;

import org.example.be_java_hisp_w26_g07.utils.UserMessageError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    @DisplayName("T-0001 userId and sellerId are the same")
    void userFollowSellerSameIds() {
        // Given - Arrange
        // When - Act
        // Then - Assert
        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class,
                () -> userImpl.userFollowSeller(1, 1));
        Assertions.assertEquals(UserMessageError.ID_CLIENT_SELLER_IS_EQUALS.getMessage(), thrown.getMessage());
    }

    @Test
    @DisplayName("T-0001 seller user does not exists")
    void userFollowSellerSellerDoesNotExist() {
        // Given - Arrange
        // Case when user (seller) with id `userToFollow` does not exist
        Mockito.when(userRepository.findById(111))
                .thenReturn(null);
        // When - Act
        // Then - Assert
        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class,
                () -> userImpl.userFollowSeller(1, 111));
        Assertions.assertEquals(UserMessageError.SELLER_NOT_FOUND.getMessage(111), thrown.getMessage());
    }

    @Test
    @DisplayName("T-0001 follower user does not exists")
    void userFollowSellerFollowerDoesNotExist() {
        // Given - Arrange
        // Case when user with id `id` does not exist
        Mockito.when(userRepository.findById(222))
                .thenReturn(null);
        Mockito.when(userRepository.findById(1))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(1, false)
                );
        // When - Act
        // Then - Assert
        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class,
                () -> userImpl.userFollowSeller(222, 1));
        Assertions.assertEquals(UserMessageError.USER_NOT_FOUND.getMessage(222), thrown.getMessage());
    }

    @Test
    @DisplayName("T-0001 seller user is not a seller")
    void userFollowSellerIsNotSeller() {
        // Given - Arrange
        // Case when user with id `userToFollow` is not a seller
        Mockito.when(userRepository.findById(9))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(9, false)
                );
        Mockito.when(userRepository.findById(1))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(1, false)
                );
        // When - Act
        // Then - Assert
        BadRequestException thrown = Assertions.assertThrows(BadRequestException.class,
                () -> userImpl.userFollowSeller(1, 9));
        Assertions.assertEquals(UserMessageError.CLIENT_IS_NOT_SELLER.getMessage(), thrown.getMessage());
    }

    @Test
    @DisplayName("T-0001 user already follows seller")
    void userFollowSellerAlreadyFollows() {
        // Given - Arrange
        // Case when user wid id `id` already follows the user with id `userToFollow`
        Mockito.when(userRepository.userFollowSeller(5,2))
                .thenReturn(true);
        Mockito.when(userRepository.findById(5))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(5, false)
                );
        Mockito.when(userRepository.findById(2))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(2, true)
                );
        // When - Act
        Boolean followAdded = userImpl.userFollowSeller(5, 2);
        // Then - Assert
        Assertions.assertFalse(followAdded);
    }

    @Test
    @DisplayName("T-0001 success add follower to seller")
    void userFollowSellerOk() {
        // Given - Arrange
        Mockito.when(userRepository.findById(9))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(9, false)
                );
        Mockito.when(userRepository.findById(1))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(1, true)
                );
        // When - Act
        boolean followAdded = userImpl.userFollowSeller(9, 1);
        // Then - Assert
        Assertions.assertTrue(followAdded);
    }

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
}
