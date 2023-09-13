package tz.go.ega.shambamkononibackend.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tz.go.ega.shambamkononibackend.userserviceImpl.UserDetailsImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${tz.go.ega.jwtkey}")
    private String key;

    Logger logger = LoggerFactory.getLogger(JWTUtils.class);


    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", new ArrayList<>())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()),SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateJwtRefreshToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("roles", new ArrayList<>())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(2)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()),SignatureAlgorithm.HS256)
                .compact();
    }


    public String getUserNameFromJwtToken(String token) {
        String username =  Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody().getSubject();
        logger.debug(username);
        return username;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: "+e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: "+e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token: "+e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: "+e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {}"+e.getMessage());
        }

        return false;
    }


}
