package com.example.BackendMIIT.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "url")
public class UrlsConfig {

    private String ia;

    public String getIa() {
        return ia;
    }

    public void setIa(String ia) {
        this.ia = ia;
    }
}
