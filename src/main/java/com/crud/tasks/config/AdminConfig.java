package com.crud.tasks.config;

import lombok.Getter;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;
}
