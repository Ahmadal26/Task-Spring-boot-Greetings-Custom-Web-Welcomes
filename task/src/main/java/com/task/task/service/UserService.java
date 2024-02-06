package com.task.task.service;

import com.task.task.bo.user.CreateUserRequest;
import com.task.task.bo.user.UpdateUserRequest;

import java.awt.image.VolatileImage;

public interface UserService  {
        void saveUser(CreateUserRequest createUserRequest);
        void updateUser(UpdateUserRequest updateUserRequest);

        void updateUserStatus(UpdateUserRequest updateUserRequest);
}
