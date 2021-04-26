package spring.boot.endterm.service.token;

import io.jsonwebtoken.impl.DefaultClaims;
import spring.boot.endterm.dto.response.TokenResponse;

import java.security.Principal;
import java.util.Map;

public interface TokenService {
    boolean tokenValidation(String token);

    Principal getAuthentication(String token);

    String generateToken(Map<String,Object> claims, Integer expirationValue, Integer duration);

    TokenResponse generateTokensResponse(Map<String,Object> claims);

    DefaultClaims getClaimsFromToken(String token);

}
