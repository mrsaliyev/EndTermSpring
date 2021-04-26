package spring.boot.endterm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.endterm.dto.request.LoginRequest;
import spring.boot.endterm.dto.request.RegisterRequest;
import spring.boot.endterm.service.user.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    private final UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest request){
        return userService.register(request);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> signIn(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @PostMapping(value = "/refresh/token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String  , String > refreshToken){
        return userService.refreshToken(refreshToken.get("refreshToken"));
    }
}
