package com.springmicroservice.gatewayservice.security.jwt;

import com.springmicroservice.gatewayservice.constants.ErrorMessage;
import com.springmicroservice.gatewayservice.exceptions.UnauthorisedException;
import com.springmicroservice.gatewayservice.security.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import static com.springmicroservice.gatewayservice.constants.SecurityConstants.AUTHORISATION_HEADER;
import static com.springmicroservice.gatewayservice.constants.SecurityConstants.BEARER_PREFIX;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    JwtProperties jwtProperties;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private String secretKey = "mysecret";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORISATION_HEADER);
        //return (!Objects.isNull(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) ? bearerToken.substring(7, bearerToken.length()) : null;
        return bearerToken;
    }

    public boolean validateToken(String token) {
        LOGGER.debug("JWT TOKEN VALIDATION STARTED");

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("Expired or invalid JWT Token");
            throw new UnauthorisedException(ErrorMessage.TokenInvalid.MESSAGE, ErrorMessage.TokenInvalid.DEVELOPER_MESSAGE);
        }
    }
}
