package com.task.task.service.User;


import com.task.task.bo.user.CreateUserRequest;
import com.task.task.bo.user.UpdateUserRequest;
import com.task.task.entity.UserEntity;

import java.util.List;

public interface UserService {

        void saveUser(CreateUserRequest createUserRequest);

        void updateUser(UpdateUserRequest updateUserRequest);

        void updateUserStatus(Long userID, UpdateUserRequest updateUserRequest);

        List<UserEntity> allUsers();

    List<String> getAllUsersWithStrongPassword();
}






