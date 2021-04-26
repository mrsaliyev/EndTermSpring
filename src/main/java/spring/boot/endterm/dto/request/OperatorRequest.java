package spring.boot.endterm.dto.request;

import lombok.Data;

@Data
public class OperatorRequest {
    private String fullName;
    private String login;
    private String password;
}
