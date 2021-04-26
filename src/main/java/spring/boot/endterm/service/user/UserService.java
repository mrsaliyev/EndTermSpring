package spring.boot.endterm.service.user;

import org.springframework.http.ResponseEntity;
import spring.boot.endterm.dto.request.*;
import spring.boot.endterm.dto.response.TokenResponse;
import spring.boot.endterm.entity.User;

public interface UserService {
    void save(User user);
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> login(LoginRequest request);
    User findByLogin(String login);
    User getAuthentication();
    ResponseEntity<TokenResponse> refreshToken(String token);
    ResponseEntity<?> blockUser(Long id);
    User findById(Long id);
    ResponseEntity<?> editProfile(String kek);
    ResponseEntity<?> changePassword(ChangePasswordRequest request);
    ResponseEntity<?> libraryOperatorsList();
    ResponseEntity<?> addOperator(OperatorRequest request);
    ResponseEntity<?> deleteOperator(Long id);
    ResponseEntity<?> editOperator(EditOperatorRequest request);
}
