package com.task.task.controller.UserController;

import com.task.task.bo.user.CreateUserRequest;
import com.task.task.bo.user.UpdateUserRequest;
import com.task.task.entity.UserEntity;
import com.task.task.service.User.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){

        this.userService = userService;

    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest){
try {
    userService.saveUser((createUserRequest));
}catch (IllegalArgumentException e ){

    return ResponseEntity.badRequest().body(e.getMessage());
}
        userService.saveUser(createUserRequest);
        return ResponseEntity.ok("User has been created !!!");



    }


@PutMapping("/update-user-status")
    public ResponseEntity<String> updateUser(@RequestParam Long userId,@RequestBody UpdateUserRequest updateUserRequest){
        try {
            userService.updateUserStatus(userId,updateUserRequest);

        }catch (IllegalArgumentException e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("User Updated successfully");
    }
    @GetMapping("/user-list")
    public List<UserEntity> getAllUsers(){
        return userService.allUsers();
    }
}
