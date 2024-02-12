package com.task.task.service.User;

import com.task.task.bo.user.CreateUserRequest;
import com.task.task.bo.user.UpdateUserRequest;
import com.task.task.entity.UserEntity;
import com.task.task.repository.UserRepository;
import com.task.task.util.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(CreateUserRequest createUserRequest) {
        UserEntity userEntity= new UserEntity();
        userEntity.setName(createUserRequest.getName());
        userEntity.setEmail(createUserRequest.getEmail());
        userEntity.setPhoneNumber(createUserRequest.getPhone());
        userEntity.setStatus(Status.valueOf(createUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {

    }

    @Override
    public void updateUserStatus(Long userID, UpdateUserRequest updateUserRequest) {
        UserEntity userEntity=userRepository.findById(userID)
                .orElseThrow();
        if(!updateUserRequest.getStatus().equals("ACTIVE") && !updateUserRequest.getStatus().equals("INACTIVE")){
            throw new IllegalArgumentException("The status should be ACTIVE or INACTIVE");
        }
        userEntity.setStatus(Status.valueOf(updateUserRequest.getStatus()));
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> allUsers() {
        return null;
    }
}