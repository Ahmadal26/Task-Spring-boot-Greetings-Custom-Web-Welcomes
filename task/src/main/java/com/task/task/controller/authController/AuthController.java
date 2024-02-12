package com.task.task.controller.authController;



import com.task.task.bo.auth.AuthinticationResponse;
import com.task.task.bo.auth.CreateLoginResquest;
import com.task.task.bo.auth.CreateSignupResquest;
import com.task.task.bo.auth.LogoutResponse;
import com.task.task.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody CreateSignupResquest createSignupRequest){
        authService.signup(createSignupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthinticationResponse> login(@RequestBody CreateLoginResquest createLoginRequest){
        AuthinticationResponse response=authService.login(createLoginRequest);
        HttpStatus status= HttpStatus.OK;
        if (response == null){
            status= HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutResponse logoutResponse){
        authService.logout(logoutResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}