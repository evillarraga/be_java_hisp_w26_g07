package org.example.be_java_hisp_w26_g07.service;


import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.repository.UserRepositoryImpl;
import org.example.be_java_hisp_w26_g07.utils.GeneratorDataTest;

import org.example.be_java_hisp_w26_g07.utils.UserMessageError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserImplTest {
    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserImpl userImpl;

    @BeforeEach
    void setUp() {
        // Case when user (seller) with id `userToFollow` does not exist
        Mockito.lenient().when(userRepository.findById(111))
                .thenReturn(null);
        // Case when user with id `id` does not exist
        Mockito.lenient().when(userRepository.findById(222))
                .thenReturn(null);
        // Case when user with id `userToFollow` is not a seller
        Mockito.lenient().when(userRepository.findById(9))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(9, false)
                );
        // Case when user wid id `id` already follows the user with id `userToFollow`
        Mockito.lenient().when(userRepository.userFollowSeller(5,2))
                .thenReturn(true);
        Mockito.lenient().when(userRepository.findById(5))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(5, false)
                );
        Mockito.lenient().when(userRepository.findById(2))
                .thenReturn(
                        GeneratorDataTest.getUserCustomId(2, true)
                );
        // Case to get the seller
        Mockito.lenient().when(userRepository.findById(1))
                .thenReturn(
                        GeneratorDataTest.getSellerUser()
                );
    }

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
        // When - Act
        Boolean followAdded = userImpl.userFollowSeller(5, 2);
        // Then - Assert
        Assertions.assertFalse(followAdded);
    }

    @Test
    @DisplayName("T-0001 success add follower to seller")
    void userFollowSellerOk() {
        // Given - Arrange
        // When - Act
        boolean followAdded = userImpl.userFollowSeller(9, 1);
        // Then - Assert
        Assertions.assertTrue(followAdded);
    }
}
