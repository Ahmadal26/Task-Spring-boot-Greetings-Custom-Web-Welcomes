package com.task.task.service;

import com.task.task.bo.user.CreateUserRequest;
import com.task.task.bo.user.UpdateUserRequest;
import com.task.task.entity.UserEntity;
import com.task.task.repository.UserRepository;
import com.task.task.util.enums.Status;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository

        ;
    }


    @Override
    public void saveUser(CreateUserRequest createUserRequest) {

        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhoneNumber());
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {

    }

    @Override
    public void updateUserStatus(UpdateUserRequest updateUserRequest) {
        UserEntity userEntity = userRepository.findById(updateUserRequest.getUserId()).orElseThrow();
        userEntity.setStatus(Status.valueOf(updateUserRequest.getStatus()));
        userRepository.save(userEntity);
    }
}
