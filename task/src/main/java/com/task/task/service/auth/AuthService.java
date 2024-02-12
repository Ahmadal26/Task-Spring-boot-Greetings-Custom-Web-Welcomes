package com.task.task.service.auth;


import com.task.task.bo.auth.AuthinticationResponse;
import com.task.task.bo.auth.CreateLoginResquest;
import com.task.task.bo.auth.CreateSignupResquest;
import com.task.task.bo.auth.LogoutResponse;

public interface AuthService {
    void signup(CreateSignupResquest createSignupRequest);

    AuthinticationResponse login(CreateLoginResquest createLoginRequest);

    void logout(LogoutResponse logoutResponse);
}