package spring.boot.endterm.dto.request;

import lombok.Data;

@Data
public class EditBookRequest {
    private Long id;
    private String name;
    private String description;
}
