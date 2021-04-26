package spring.boot.endterm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.boot.endterm.dto.request.RegisterRequest;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
//

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "login")
    private String login;


    @Column(name = "password")
    private String password;

    @Column(name = "blocked")
    private boolean blocked;


    @ManyToMany
    @JoinTable(name = "user_roles" ,
                joinColumns = @JoinColumn(
                        name = "user_id" , referencedColumnName = "id"
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "role_id" , referencedColumnName = "id"
                )
    )
    private List<Role> roles;

    public User(RegisterRequest request , List<Role> roles) {
        this.fullName = request.getFullName();
        this.login = request.getLogin();
        this.password = request.getPassword();
        this.roles = roles;
    }

    public User(String fullName, String login, String password, List<Role> roles) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

}
