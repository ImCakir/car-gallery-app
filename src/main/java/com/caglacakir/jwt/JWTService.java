package com.caglacakir.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    public static final String SECRET_KEY = "UmvJskrtCgdeO8pQXgJR3NsZ9Hb0bDq+MWNNeVjhics=";

    // generetaToken --> userdetails objesi alır.
    // token oluşturduk
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt((new Date()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2)) // 2 daat
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    // claims gibi expiration , subject gibi değerleri göre tokenn çözdük
    public <T> T exportToken(String token, Function<Claims, T> claimsFunc) {
        Claims claims = getClaims(token);
        return claimsFunc.apply(claims);
    }


// claimsleri veren yer
    public Claims getClaims(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();

        return claims;
    }

    // username çekildi
    public String getUsernameByToken(String token){ // token geçerli mi
        return exportToken(token, Claims::getSubject);
    }

    // key ayarladık
    public boolean isTokenValid(String token) {
        Date expireDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }

    public Key getKey(){
      byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
      return Keys.hmacShaKeyFor(bytes);
    }
}
