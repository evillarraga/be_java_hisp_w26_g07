package org.example.be_java_hisp_w26_g07.repository;

import org.example.be_java_hisp_w26_g07.utils.GeneratorDataTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl(GeneratorDataTest.findUsers());
    }

    @Test
    @DisplayName("T-0001 is user following seller")
    void userFollowSellerTest() {
        // Given - Arrange
        // When - Act
        boolean alreadyFollows = userRepository.userFollowSeller(1, 2);
        boolean isNotFollowing = userRepository.userFollowSeller(7, 7);
        // Then - Assert
        Assertions.assertTrue(alreadyFollows);
        Assertions.assertFalse(isNotFollowing);
    }

    @Test
    @DisplayName("T-0001 add follow")
    void addFollowerByIdTest() {
        // Given - Arrange
        // When - Act
        boolean followAdded = userRepository.addFollowerById(8, 5);
        // Then - Assert
        Assertions.assertTrue(followAdded);
    }
}
