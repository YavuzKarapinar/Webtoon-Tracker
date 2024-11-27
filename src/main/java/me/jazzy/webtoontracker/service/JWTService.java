package me.jazzy.webtoontracker.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import me.jazzy.webtoontracker.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private final String USERNAME_KEY = "Username";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry}")
    private Long expiry;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secret.getBytes());
    }

    public String generateJWT(User user) {
        return JWT.create()
                .withIssuer("admin")
                .withClaim(USERNAME_KEY, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .sign(algorithm);
    }

    public String getUsername(String token) {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);

        return jwt.getClaim(USERNAME_KEY).asString();
    }

    public boolean isTokenVerified(String token) {
        try {
            JWT.require(algorithm).build().verify(token);
            return false;
        } catch (JWTVerificationException ignored) {
            return true;
        }
    }

}