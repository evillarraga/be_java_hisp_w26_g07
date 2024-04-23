package org.example.be_java_hisp_w26_g07;

import org.example.be_java_hisp_w26_g07.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataConfiguration {

    @Bean
    public List<User> getUsers() {
        return List.of();
    }
}
