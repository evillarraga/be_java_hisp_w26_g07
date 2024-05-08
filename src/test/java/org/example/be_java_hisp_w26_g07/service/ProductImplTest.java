package org.example.be_java_hisp_w26_g07.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.be_java_hisp_w26_g07.dto.products.PostDto;
import org.example.be_java_hisp_w26_g07.dto.products.ProductDto;
import org.example.be_java_hisp_w26_g07.dto.users.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.entity.Post;
import org.example.be_java_hisp_w26_g07.entity.User;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.example.be_java_hisp_w26_g07.utils.GeneratorDataTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.example.be_java_hisp_w26_g07.repository.interfaces.IUserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

@ExtendWith(MockitoExtension.class)
class ProductImplTest {
    @Mock
    private IUserRepository iUserRepository;

    @InjectMocks
    private ProductImpl productImpl;
    private UserImpl userImpl;



    @DisplayName("T-0005 - Verificar que el tipo de ordenamiento por fecha exista (US-0009) date_asc")
    @Test
    void verifyNormalContinuityOfSortingByDateAscTest() {
        // Arrange
        String order = "date_asc";
        Integer userID = 2;
        List<Post> posts = GeneratorDataTest.getListOfSellersLastTwoWeeks();
        User user = GeneratorDataTest.findUsers().get(1);
        when(iUserRepository.findById(userID)).thenReturn(user);
        when(iUserRepository.findProductByFollow(user)).thenReturn(posts);

        // Act
        productImpl.findProductByFollow(userID, order);
        // Assert
        verify(iUserRepository, atLeast(1)).findById(any(Integer.class));
    }

    @DisplayName("T-0005 - Verificar que el tipo de ordenamiento por fecha exista (US-0009) date_desc")
    @Test
    void verifyNormalContinuityOfSortingByDateDescTest() {
        // Arrange
        String order = "date_desc";
        Integer userID = 2;
        List<Post> posts = GeneratorDataTest.getListOfSellersLastTwoWeeks();
        User user = GeneratorDataTest.findUsers().get(1);
        when(iUserRepository.findById(userID)).thenReturn(user);
        when(iUserRepository.findProductByFollow(user)).thenReturn(posts);

        // Act
        productImpl.findProductByFollow(userID, order);
        // Assert
        verify(iUserRepository, atLeast(1)).findById(any(Integer.class));
    }

    @DisplayName("T-0005 - Verificar que el tipo de ordenamiento por fecha no se permita por tipo de orden incorrecto")
    @Test
    void verifyThatTheProcessIsNotExecutedDueToBadDataType() {
        // Arrange
        String order = "incorrect_data";
        Integer userID = 2;
        // Act - Assert
        assertThrows(BadRequestException.class, () -> productImpl.findProductByFollow(userID, order));
    }

    @Test
    @DisplayName("T-0008 Verificar que la consulta de publicaciones realizadas en las últimas dos semanas de un determinado vendedor sean efectivamente de las últimas dos semanas. (US-0006)")
    void findProductByFollowTest() {

        //Arrange
        Integer userId = 1;
        User userMock = GeneratorDataTest.findUsers().get(0);
        List<Post> postMockList =GeneratorDataTest.getListOfSellersLastTwoWeeks();
        ObjectMapper mapper = new ObjectMapper();
        List<PostDto> expected= mapper.convertValue(postMockList, new TypeReference<List<PostDto>>() {});
        when(iUserRepository.findById(userId)).thenReturn(userMock);
        when(iUserRepository.findProductByFollow(userMock)).thenReturn(postMockList);

        //Act
        List<PostDto> output = productImpl.findProductByFollow(userId,null);

        //Assert
        assertEquals(expected, output);
    }

}