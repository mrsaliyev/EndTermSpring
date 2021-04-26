package spring.boot.endterm.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String login;
    private String password;
}
