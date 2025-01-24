package com.pintoss.gitftmall.domain.membership.application;

import com.pintoss.gitftmall.infra.config.security.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenManageService {

    private final TokenProvider tokenProvider;

    public String createToken(String subject, boolean isRefresh) {
        if(isRefresh){
            return tokenProvider.createRefreshToken(subject);
        }
        return tokenProvider.createAccessToken(subject);
    }

    public boolean isValidToken(String token) {
        return tokenProvider.validateToken(token);
    }

    public String getSubject(String token) {
        return tokenProvider.getSubject(token);
    }

    public boolean isExpiredToken(String token) {
        try{
            Claims claims = tokenProvider.getClaims(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e){
            return true;
        } catch (Exception e){
            return true;
        }
    }
}
