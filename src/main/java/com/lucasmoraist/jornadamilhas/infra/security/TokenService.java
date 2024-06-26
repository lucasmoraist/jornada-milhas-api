package com.lucasmoraist.jornadamilhas.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucasmoraist.jornadamilhas.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwt;

    public String generateToken(User user){

        try{
            Algorithm algorithm = Algorithm.HMAC256(jwt);

            String token = JWT.create()
                    .withIssuer("jornada-milhas-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirateDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwt);
            return JWT.require(algorithm)
                    .withIssuer("jornada-milhas-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException e){
            return null;
        }
    }

    private Instant genExpirateDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
