package com.springmicroservice.gatewayservice.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
//@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey = "mysecret";

    public String getSecretKey() {
        return secretKey;
    }
}
