package io.stream.com.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.stream.com.models.User;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    @Value("${service.jwt.key}")
    private String key;

    private final static int DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 12;

	public String getUsername(String token) { return getClaim(token, Claims::getSubject); }

    public Date getExpirationDate(String token) { return getClaim(token, Claims::getExpiration); }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) { return claimsResolver.apply(getAllClaims(token)); }

    private Claims getAllClaims(String token) { return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); }

    private Boolean isExpired(String token) { return getExpirationDate(token).before(new Date()); }

    public String generateToken(User user) { return createToken(new HashMap<String, Object>(), user.getUsername()); }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + DAY_IN_MILLISECONDS))
                .signWith(SignatureAlgorithm.HS256, key).compact();
    }

    public Boolean validateToken(String token, User user) { return (getUsername(token).equals(user.getUsername()) && !isExpired(token)); }
    
}