package com.example.UserAPIMongoDB.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper ModelMapperConfig() {
        return new ModelMapper();
    } //Inject this bean into the context of the application
}
