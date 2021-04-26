package spring.boot.endterm.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPasswd;
    private String newPasswd;
    private String confPasswd;
}
