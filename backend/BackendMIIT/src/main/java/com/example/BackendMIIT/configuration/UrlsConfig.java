package com.example.BackendMIIT.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "url")
public class UrlsConfig {

    private String ia;
    private String miitBaseUrl;
    private String eduPrograms;
}
