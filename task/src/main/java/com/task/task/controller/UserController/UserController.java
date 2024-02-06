package com.task.task.controller.UserController;

import com.task.task.bo.user.CreateUserRequest;
import com.task.task.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){

        this.userService = userService;

    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest){

        userService.saveUser(createUserRequest);
        return ResponseEntity.ok("User has been created !!!");
    }
}
