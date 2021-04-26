package spring.boot.endterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.endterm.entity.Role;
import spring.boot.endterm.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    Optional<User> findById(Long id);
    List<User> findAllByRoles(Role role);
}
