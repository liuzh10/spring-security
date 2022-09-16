package com.liuzh10.utli;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liuzh10.exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;

import java.util.Date;

@EnableConfigurationProperties(JwtUtils.class)
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtUtils {

    /**
     * secret
     */
    private String secret;
    /**
     * expire time
     */
    private int expire;
    /**
     * storage in header
     */
    private String header;


    /**
     * create jwt token
     *
     * @param username
     * @return token
     */
    public String generateToken(String username) {
        Date nowDate = new Date();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(DateUtils.addDays(nowDate, expire))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * analysis token，
     *
     * @param token token
     * @return Map<String, Object>
     */
    private Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * token Expires
     *
     * @return true：expire
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }


    public String getUsernameFromToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new MyException(HttpStatus.UNAUTHORIZED.value(), "invalid token");
        }
        Claims claims = getClaimByToken(token);
        if (claims == null || isTokenExpired(claims.getExpiration())) {
            throw new MyException(HttpStatus.UNAUTHORIZED.value(), header + "invalid，Please log in again");
        }
        return claims.getSubject();
    }
}
