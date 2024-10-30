package com.project.COLLEGEERP.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        String jwts = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("userId", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        return jwts;
    }

    public String generateUserIdFromToken(String token){
        token=token.substring(7);
        Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String userId=String.valueOf(claims.get("userId"));
        return userId;
    }

    private static String populateAuthorities(Collection<? extends  GrantedAuthority> authorities){
        Set<String> auth=new HashSet<>();

       for(GrantedAuthority ga:authorities){
          auth.add(ga.getAuthority());
       }

       return String.join(",",auth);
    }


}
