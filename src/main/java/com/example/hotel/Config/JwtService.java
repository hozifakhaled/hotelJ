package com.example.hotel.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service

public class JwtService {
    private static final String SECRET_KEY = "HuZaiFa_SuperSecureKey2025!JWT#Token@";
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }


    public  <T> T extractClaims (String token , Function<Claims, T> claimsResolver) {
final  Claims claims = extractAllClaims(token);
return claimsResolver.apply(claims);
    }



    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }



    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
          .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(
            Map<String, Object> claims,
            UserDetails userDetails) {


        return Jwts.builder()
                .setClaims(claims).setSubject(userDetails.getUsername())
                // .claim("employerId", customUser.getEmployerId()) // ✅ هنا الإضافة الجديدة
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername())&& !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
