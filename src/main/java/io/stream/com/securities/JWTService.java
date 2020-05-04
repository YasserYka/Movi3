package io.stream.com.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.stream.com.models.User;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTService {

    @Value("${service.jwt.key}")
    private String key;

	public String extractUsername(String token) { return extractClaim(token, Claims::getSubject); }

    public Date extractExpiration(String token) { return extractClaim(token, Claims::getExpiration); }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { return claimsResolver.apply(extractAllClaims(token)); }
    private Claims extractAllClaims(String token) { return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); }

    private Boolean isTokenExpired(String token) { return extractExpiration(token).before(new Date()); }

    public String generateToken(User user) { return createToken(new HashMap<String, Object>(), user.getUsername()); }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }

    public Boolean validateToken(String token, User user) { return (extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token)); }
    
}