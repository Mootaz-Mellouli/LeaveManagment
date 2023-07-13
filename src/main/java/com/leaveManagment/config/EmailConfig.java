package com.leaveManagment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Bean
    public String fromEmail() {
        return "amdouni.spring@gmail.com";
    }
}
