package org.example.be_java_hisp_w26_g07.service;

import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.example.be_java_hisp_w26_g07.service.interfaces.IProductService;
import org.example.be_java_hisp_w26_g07.utils.DataGeneratorTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductImplTest {

    @Mock
    IUserRepository userRepository;

    @InjectMocks
    ProductImpl productService;

    @DisplayName("T-0005 - Verificar que el tipo de ordenamiento por fecha exista (US-0009) date_asc")
    @Test
    void verifyNormalContinuityOfSortingByDateAscTest() {
        // Arrange
        String order = "date_asc";
        Integer userID = 2;
        List<Post> posts = DataGeneratorTest.getListOfSellersLastTwoWeeks();
        User user = new User(2, "Santiago", true);
        when(userRepository.findById(userID)).thenReturn(user);
        when(userRepository.findProductByFollow(user)).thenReturn(posts);

        // Act
        productService.findProductByFollow(userID, order);
        // Assert
        verify(userRepository, atLeast(1)).findById(any(Integer.class));
    }

    @DisplayName("T-0005 - Verificar que el tipo de ordenamiento por fecha exista (US-0009) date_desc")
    @Test
    void verifyNormalContinuityOfSortingByDateDescTest() {
        // Arrange
        String order = "date_desc";
        Integer userID = 2;
        List<Post> posts = DataGeneratorTest.getListOfSellersLastTwoWeeks();
        User user = new User(2, "Santiago", true);
        when(userRepository.findById(userID)).thenReturn(user);
        when(userRepository.findProductByFollow(user)).thenReturn(posts);

        // Act
        productService.findProductByFollow(userID, order);
        // Assert
        verify(userRepository, atLeast(1)).findById(any(Integer.class));
    }

    @DisplayName("T-0005 - Verificar que el tipo de ordenamiento por fecha no se permita por tipo de orden incorrecto")
    @Test
    void verifyThatTheProcessIsNotExecutedDueToBadDataType() {
        // Arrange
        String order = "incorrect_data";
        Integer userID = 2;
        // Act - Assert
        assertThrows(BadRequestException.class, () -> productService.findProductByFollow(userID, order));
    }

    @Test
    void findProductByFollow() {

    }

    @Test
    void createPost() {
    }
}