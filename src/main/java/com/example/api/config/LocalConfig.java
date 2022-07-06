package com.example.api.config;

import com.example.api.domain.User;
import com.example.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;


    @Bean
    public void startDB(){
        User u1 = new User(null, "werner", "werner@gmail.com", "admin");
        User u2 = new User(null, "rosy", "rosy@gmail.com", "admin");

        repository.saveAll(List.of(u1, u2));

    }
}
