package com.proyecto.wordle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {
    @Bean
    public org.modelmapper.ModelMapper modelMapper(){
        return new org.modelmapper.ModelMapper();
    }
}