package org.example.be_java_hisp_w26_g07.repository;

import org.example.be_java_hisp_w26_g07.DataConfiguration;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@SpringJUnitConfig(classes = DataConfiguration.class)
class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByIdTest() {
        //Arrange
        User expected = new User(1, "Monica Test", true);

        //Act
        User output = userRepository.findById(1);

        //Assert
        assertEquals(expected, output);
    }

    @Test
    void findByIdNullTest() {
        //Act
        User output = userRepository.findById(20);

        //Assert
        assertNull(output);
    }

    /*
     return followersList.stream()
                .filter(f -> f.getSellerId().equals(sellerId))
                .mapToInt(Followers::getUserId)
                .boxed().toList();
    * */

    @Test
    void followerIdBySellerIdTest() {
        //Arrange
        List<Integer> expected = List.of(2,3,4,8,9);
        Integer userId = 1;

        //Act
        List<Integer> output = userRepository.followerIdBySellerId(userId);

        assertEquals(expected, output);
    }

    @Test
    void followerIdBySellerIdEmptyListTest() {
        //Arrange
        List<Integer> expected = List.of();
        Integer userId = 10;

        //Act
        List<Integer> output = userRepository.followerIdBySellerId(userId);

        assertEquals(expected, output);
    }

    @Test
    void findAll() {
    }

    @Test
    void userFollowSeller() {
    }

    @Test
    void addFollowerById() {
    }

    @Test
    void findProductByFollow() {
    }

    @Test
    void unfollow() {
    }
}