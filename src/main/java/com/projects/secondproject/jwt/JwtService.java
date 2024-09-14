package com.projects.secondproject.jwt;

import com.projects.secondproject.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final String SECRET_KEY= "cLAcPiIwVfiz1pVYnLas444n4oedOdQgXewae466VeLEet244";

    public String exactUsername(String token){
        return exactClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails userDetails){
        String username = exactUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return exactExpiration(token).before(new Date());
    }

    private Date exactExpiration(String token) {
        return exactClaim(token,Claims::getExpiration);
    }

    public <T> T exactClaim(String token, Function<Claims,T> resolver){
        Claims claims = exactAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims exactAllClaims(String token){
        return  Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToke(User user){
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = Jwts
                .builder()
                .setSubject(user.getUsername())
                .claim("roles",roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +24*60*60*1000))
                .signWith(getSignInKey())
                .compact();
        return token;
    }
    public SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
