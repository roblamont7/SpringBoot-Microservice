package com.springmicroservice.loginservice.jwt;

import com.springmicroservice.loginservice.dto.NetworkResponseDTO;
import com.springmicroservice.loginservice.utils.NetworkUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import static com.springmicroservice.loginservice.constants.JWTConstants.JWT_KEY;

@Component
public class JwtTokenProvider {

    @Autowired
    JwtProperties jwtProperties;

    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }

    public String createToken(String username, HttpServletRequest request) {
        NetworkResponseDTO networkDTO = NetworkUtils.getDeviceAddress.apply(request);
        return Jwts.builder()
                .setSubject(username)
                .claim("mac", networkDTO.getMacAddress())
                .claim("ip", networkDTO.getIpAddress())
                .setIssuer(JWT_KEY)
                .setExpiration(calculateExpressionDate())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Date calculateExpressionDate() {
        Date currentDate = new Date();
        return new Date(currentDate.getTime() + jwtProperties.getValidityInMilliseconds());
    }
}
