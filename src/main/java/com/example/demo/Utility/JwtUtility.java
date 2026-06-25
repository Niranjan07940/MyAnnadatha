package com.example.demo.Utility;

import com.example.demo.Beans.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtility {

    private final String SECRET="This is secret key 9849090640 my phone number";
    private final SecretKey secretKey= Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .claim("userId",user.getId())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    public String getUsername(String token){
        Claims body=extractClaims(token);
        return body.getSubject();
    }
    public boolean isTokenExpired(String token){
      Claims body=extractClaims(token);
      return body.getExpiration().before(new Date());
    }

    public boolean validateToken(String username, UserDetails userDetails,String token) {
        //TODO -check if username is same as username in user details
        //TODO -check if the token is expired
        return username.equals(userDetails.getUsername()) &&  !isTokenExpired(token);



    }
}
