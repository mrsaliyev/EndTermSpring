package spring.boot.endterm.dto.response;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String role;
    public TokenResponse(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public TokenResponse(String accessToken, String refreshToken, String role){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
    }

}
