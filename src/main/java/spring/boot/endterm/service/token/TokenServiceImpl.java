package spring.boot.endterm.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import spring.boot.endterm.constants.ClaimKeysConstants;
import spring.boot.endterm.dto.response.TokenResponse;
import spring.boot.endterm.service.userDetails.UserDetailsImpl;
import spring.boot.endterm.service.userDetails.UserDetailsServiceImpl;

import java.security.Principal;
import java.util.Calendar;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService{
    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    @Value("${token.secret.password}")
    private String SECRET;
    private final long ONE_MINUTE = 60_000; // 60 seconds
    private final long ONE_HOUR = 36000_0000; // 1 hour

    private final String TOKEN_PREFIX = "Bearer ";
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public TokenServiceImpl(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    public boolean tokenValidation(String token) {
        DefaultClaims claims;
        String username;
        try {
            if (token == null || token.isEmpty())
                return false;
            if (token.contains(TOKEN_PREFIX))
                token = token.replace(TOKEN_PREFIX, "");
            claims = (DefaultClaims) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            username = claims.get(ClaimKeysConstants.USERNAME, String.class);
            return (username != null || !username.isEmpty());
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Principal getAuthentication(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        String username = claims.getBody().get(ClaimKeysConstants.USERNAME).toString();

        UserDetailsImpl userDetails = (UserDetailsImpl) this.userDetailsServiceImpl.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @Override
    public String generateToken(Map<String, Object> claims, Integer expirationValue, Integer duration) {
        Calendar calendar = Calendar.getInstance();
        claims.put(ClaimKeysConstants.TOKEN_CREATE_DATE, calendar.getTime());
        calendar.add(duration, expirationValue);
        claims.put(ClaimKeysConstants.TOKEN_EXPIRE_DATE, calendar.getTime());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }

    @Override
    public TokenResponse generateTokensResponse(Map<String, Object> claims) {

        claims.put(ClaimKeysConstants.IS_REFRESH_TOKEN, false);
        String accessToken = generateToken(claims, 1, Calendar.HOUR);
        claims.put(ClaimKeysConstants.IS_REFRESH_TOKEN, true);
        String refreshToken = generateToken(claims, 1, Calendar.MONTH);

        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public DefaultClaims getClaimsFromToken(String token) {
        DefaultClaims claims;
        try {
            if (token.contains(TOKEN_PREFIX))
                token = token.replace(TOKEN_PREFIX, "");
            claims = (DefaultClaims) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            System.err.println("Token claims invalid exception. Exception message :" + e.getMessage());
            return null;
        }
    }

}
