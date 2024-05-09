package org.example.be_java_hisp_w26_g07.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private ObjectWriter objectWriter;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectWriter = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
    }

    @Test
    @DisplayName("US-0005: crear un nuevo post - body correcto")
    public void createPost() throws Exception {
        String body = "{" +
                "\"user_id\":1," +
                "\"date\":\"29-04-2021\"," +
                "\"product\":{" +
                "\"product_id\":10," +
                "\"product_name\":\"Mouse\"," +
                "\"type\":\"Gamer\"," +
                "\"brand\":\"Logitech\"," +
                "\"color\":\"Black\"," +
                "\"notes\":\"Special Edition\"" +
                "}," +
                "\"category\":100," +
                "\"price\":1500.50" +
                "}";
        mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(1))
                .andExpect(jsonPath("$.product.product_name").value("Mouse"))
                .andExpect(jsonPath("$.product.color").value("Black"));
    }

    @Test
    @DisplayName("US-0005: crear un nuevo post - bad request por fecha")
    public void createPostBadRequestDate() throws Exception {
        String body = "{" +
                "\"user_id\":1," +
                "\"date\":\"32-04-2021\"," +
                "\"product\":{" +
                "\"product_id\":10," +
                "\"product_name\":\"Mouse\"," +
                "\"type\":\"Gamer\"," +
                "\"brand\":\"&Logitech&\"," +
                "\"color\":\"&Black&\"," +
                "\"notes\":\"Special Edition\"" +
                "}," +
                "\"category\":100," +
                "\"price\":1500.50" +
                "}";
        mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Formato no valido"));
    }

    @Test
    @DisplayName("US-0005: crear un nuevo post - bad request por fecha")
    public void createPostBadRequest() throws Exception {
        String body = "{" +
                "\"user_id\":1," +
                "\"date\":\"22-04-2021\"," +
                "\"product\":{" +
                "\"product_id\":10," +
                "\"product_name\":\"Mouse\"," +
                "\"type\":\"Gamer\"," +
                "\"brand\":\"&Logitech&\"," +
                "\"color\":\"&Black&\"," +
                "\"notes\":\"Special Edition\"" +
                "}," +
                "\"category\":100," +
                "\"price\":1500.50" +
                "}";
        mockMvc.perform(post("/products/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
