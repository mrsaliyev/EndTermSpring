package spring.boot.endterm.dto.request;

import lombok.Data;

@Data
public class EditOperatorRequest {
    private Long id;
    private String fullName;
    private String password;
}
