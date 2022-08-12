package com.example.monolith.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.monolith.key.service.SecretKeyServiceImpl;
import com.example.monolith.services.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

@Service
@AllArgsConstructor
public class TokenGenerator {


    private SecretKeyServiceImpl secretKeyServiceImpl;
    private UserServiceImpl userServiceImpl;


    public String accessToken(UserDetails user, HttpServletRequest request, HttpServletResponse response) {
        Algorithm algorithm = getAlgorithm();
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        return accessToken;
    }

    public String refreshToken(UserDetails user, HttpServletRequest request, HttpServletResponse response) {
        Algorithm algorithm = getAlgorithm();
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 40 * 60 * 1000))
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm);
        return refreshToken;
    }


    public Algorithm getAlgorithm() {
        Algorithm algorithm = Algorithm.HMAC256(secretKeyServiceImpl.getKey());
        return algorithm;
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = getAlgorithm();
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                Date refreshExpiresAt = decodedJWT.getExpiresAt();
                UserDetails user = userServiceImpl.loadUserByUsername(username);
                Date accessExpiresAt = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
                Duration RefreshTimeToLive = Duration.between(new Date().toInstant(), refreshExpiresAt.toInstant());
                if (RefreshTimeToLive.getSeconds() < 30 * 60) {
                    refreshExpiresAt = new Date(System.currentTimeMillis() + 40 * 60 * 1000);
                }
                HashMap<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken(user,request,response));
                tokens.put("refreshToken", refreshToken(user,request,response));
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {

            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }


    }
}
