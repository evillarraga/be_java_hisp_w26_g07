package org.example.be_java_hisp_w26_g07.service;


import org.example.be_java_hisp_w26_g07.dto.users.FollowedResponseDto;
import org.example.be_java_hisp_w26_g07.dto.users.UserInfoFollowsDto;
import org.example.be_java_hisp_w26_g07.entity.User;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserImplTest {
    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserImpl userImpl;

    @Test
    @DisplayName("T-0004 Verificar el correcto ordenamiento ascendente por nombre")
    void findFollowedUsersAsc() {
        //Arrange
        Integer userId = 3;
        User user3 = GeneratorDataTest.findUsers().get(2);
        User user1 = GeneratorDataTest.findUsers().get(0);
        User user2 = GeneratorDataTest.findUsers().get(1);
        User user5 = GeneratorDataTest.findUsers().get(4);
        User user6 = GeneratorDataTest.findUsers().get(5);
        User user9 = GeneratorDataTest.findUsers().get(8);

        when(userRepository.findById(userId)).thenReturn(user3);

        when(userRepository.findById(1)).thenReturn(user1);
        when(userRepository.findById(2)).thenReturn(user2);
        when(userRepository.findById(5)).thenReturn(user5);
        when(userRepository.findById(6)).thenReturn(user6);
        when(userRepository.findById(9)).thenReturn(user9);
        FollowedResponseDto nameAsc = userImpl.findFollowedUsers(userId, "name_asc");
        List<UserInfoFollowsDto> ascFollowed = nameAsc.getFollowed();

        Assertions.assertEquals(5,ascFollowed.size());
        Assertions.assertEquals("Bryann", ascFollowed.get(0).getName());
        Assertions.assertEquals("Carlos", ascFollowed.get(1).getName());
        Assertions.assertEquals("Martin", ascFollowed.get(2).getName());
        Assertions.assertEquals("Monica", ascFollowed.get(3).getName());
        Assertions.assertEquals("Santiago", ascFollowed.get(4).getName());

    }

    @Test
    @DisplayName("T-0004 Verificar el correcto ordenamiento descendente por nombre")
    void findFollowedUsersDesc() {
        //Arrange
        Integer userId = 3;
        User user3 = GeneratorDataTest.findUsers().get(2);
        User user1 = GeneratorDataTest.findUsers().get(0);
        User user2 = GeneratorDataTest.findUsers().get(1);
        User user5 = GeneratorDataTest.findUsers().get(4);
        User user6 = GeneratorDataTest.findUsers().get(5);
        User user9 = GeneratorDataTest.findUsers().get(8);

        when(userRepository.findById(userId)).thenReturn(user3);

        when(userRepository.findById(1)).thenReturn(user1);
        when(userRepository.findById(2)).thenReturn(user2);
        when(userRepository.findById(5)).thenReturn(user5);
        when(userRepository.findById(6)).thenReturn(user6);
        when(userRepository.findById(9)).thenReturn(user9);
        FollowedResponseDto nameAsc = userImpl.findFollowedUsers(userId, "name_desc");
        List<UserInfoFollowsDto> ascFollowed = nameAsc.getFollowed();

        Assertions.assertEquals(5,ascFollowed.size());
        Assertions.assertEquals("Santiago", ascFollowed.get(0).getName());
        Assertions.assertEquals("Monica", ascFollowed.get(1).getName());
        Assertions.assertEquals("Martin", ascFollowed.get(2).getName());
        Assertions.assertEquals("Carlos", ascFollowed.get(3).getName());
        Assertions.assertEquals("Bryann", ascFollowed.get(4).getName());

    }

    @Test
    @DisplayName("T-0004 Envia la lista de los seguidos por un vendedor")
    void findFollowedUsers() {
        //Arrange
        Integer userId = 3;
        User user3 = GeneratorDataTest.findUsers().get(2);
        User user1 = GeneratorDataTest.findUsers().get(0);
        User user2 = GeneratorDataTest.findUsers().get(1);
        User user5 = GeneratorDataTest.findUsers().get(4);
        User user6 = GeneratorDataTest.findUsers().get(5);
        User user9 = GeneratorDataTest.findUsers().get(8);

        when(userRepository.findById(userId)).thenReturn(user3);

        when(userRepository.findById(1)).thenReturn(user1);
        when(userRepository.findById(2)).thenReturn(user2);
        when(userRepository.findById(5)).thenReturn(user5);
        when(userRepository.findById(6)).thenReturn(user6);
        when(userRepository.findById(9)).thenReturn(user9);
        FollowedResponseDto nameAsc = userImpl.findFollowedUsers(userId, null);
        List<UserInfoFollowsDto> ascFollowed = nameAsc.getFollowed();

        Assertions.assertEquals(5,ascFollowed.size());
        Assertions.assertEquals("Monica", ascFollowed.get(0).getName());
        Assertions.assertEquals("Santiago", ascFollowed.get(1).getName());
        Assertions.assertEquals("Bryann", ascFollowed.get(2).getName());
        Assertions.assertEquals("Carlos", ascFollowed.get(3).getName());
        Assertions.assertEquals("Martin", ascFollowed.get(4).getName());

    }

    @Test
    @DisplayName("T-0004 Envia la lista de los seguidos por un vendedor sin orden")
    void findFollowedUsersByAnyOrder() {
        //Arrange
        Integer userId = 3;
        User user3 = GeneratorDataTest.findUsers().get(2);
        User user1 = GeneratorDataTest.findUsers().get(0);
        User user2 = GeneratorDataTest.findUsers().get(1);
        User user5 = GeneratorDataTest.findUsers().get(4);
        User user6 = GeneratorDataTest.findUsers().get(5);
        User user9 = GeneratorDataTest.findUsers().get(8);

        when(userRepository.findById(userId)).thenReturn(user3);

        when(userRepository.findById(1)).thenReturn(user1);
        when(userRepository.findById(2)).thenReturn(user2);
        when(userRepository.findById(5)).thenReturn(user5);
        when(userRepository.findById(6)).thenReturn(user6);
        when(userRepository.findById(9)).thenReturn(user9);
        FollowedResponseDto nameAsc = userImpl.findFollowedUsers(userId, "invalid");
        List<UserInfoFollowsDto> ascFollowed = nameAsc.getFollowed();

        Assertions.assertEquals(5,ascFollowed.size());
        Assertions.assertEquals("Monica", ascFollowed.get(0).getName());
        Assertions.assertEquals("Santiago", ascFollowed.get(1).getName());
        Assertions.assertEquals("Bryann", ascFollowed.get(2).getName());
        Assertions.assertEquals("Carlos", ascFollowed.get(3).getName());
        Assertions.assertEquals("Martin", ascFollowed.get(4).getName());

    }


}