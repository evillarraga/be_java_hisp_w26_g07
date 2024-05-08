package org.example.be_java_hisp_w26_g07.integrations;

import org.example.be_java_hisp_w26_g07.controller.UserController;
import org.example.be_java_hisp_w26_g07.dto.users.CountFollowersResponseDto;
import org.example.be_java_hisp_w26_g07.exception.BadRequestException;
import org.example.be_java_hisp_w26_g07.exception.NotFoundException;
import org.example.be_java_hisp_w26_g07.utils.UserMessageError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private UserController userController;

    @Test
    public void testCountFollowers() throws Exception {
        CountFollowersResponseDto expected = new CountFollowersResponseDto(1, "Monica", 3);

        mockMvc.perform(get("/users/{userId}/followers/count", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user_id").value(expected.getId()))
                .andExpect(jsonPath("$.user_name").value(expected.getName()))
                .andExpect(jsonPath("$.followers_count").value(expected.getFollowersCount()));
    }

    @Test
    public void testCountFollowersUserNotFound() throws Exception {
        Integer userId = 100;
        NotFoundException expected = new NotFoundException(UserMessageError.USER_NOT_FOUND.getMessage(100));

        mockMvc.perform(get("/users/{userId}/followers/count", userId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(expected.getMessage()));
    }

    @Test
    public void testCountFollowersBadRequest() throws Exception {
        Integer userId = 9;
        BadRequestException expected = new BadRequestException(UserMessageError.CLIENT_IS_NOT_SELLER.getMessage());

        mockMvc.perform(get("/users/{userId}/followers/count", userId))
                .andDo(print())
                .andExpect(status().isNotAcceptable())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(expected.getMessage()));
    }
}
