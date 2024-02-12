package com.task.task.service.auth;

import com.task.task.bo.auth.AuthinticationResponse;
import com.task.task.bo.auth.CreateLoginResquest;
import com.task.task.bo.auth.CreateSignupResquest;
import com.task.task.bo.auth.LogoutResponse;
import com.task.task.bo.customUserDetailes.CustomUserDetails;
import com.task.task.config.JWTUtil;
import com.task.task.entity.RoleEntity;
import com.task.task.entity.UserEntity;
import com.task.task.repository.RoleRepository;
import com.task.task.repository.UserRepository;
import com.task.task.util.enums.Roles;
import com.task.task.util.enums.Status;
import com.task.task.util.excption.BodyGuardException;
import com.task.task.util.excption.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService userDetailsService;

    private final JWTUtil jwtUtil;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JWTUtil jwtUtil, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void signup(CreateSignupResquest createSignupRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Roles.user.name())
                .orElseThrow(() -> new BodyGuardException("no Roles Found"));;
        UserEntity user= new UserEntity();
        user.setName(createSignupRequest.getName());
        user.setUsername(createSignupRequest.getUsername());
        user.setPhoneNumber(createSignupRequest.getPhoneNumber());
        user.setEmail(createSignupRequest.getEmail());
        user.setRoles(roleEntity);
        user.setStatus(Status.ACTIVE);
        user.setPassword(bCryptPasswordEncoder.encode(createSignupRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public AuthinticationResponse login(CreateLoginResquest createLoginRequest) {
        requiredNonNull(createLoginRequest.getUsername(),"username");
        requiredNonNull(createLoginRequest.getPassword(),"password");

        String username= createLoginRequest.getUsername().toLowerCase();
        String password= createLoginRequest.getPassword();

        authentication(username, password);

        CustomUserDetails userDetails= userDetailsService.loadUserByUsername(username);

        String accessToken = jwtUtil.generateToken(userDetails);

        AuthinticationResponse response = new AuthinticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer "+accessToken);
        return response;
    }

    @Override
    public void logout(LogoutResponse logoutResponse) {
        requiredNonNull(logoutResponse.getToken(),"token");
    }

    private void requiredNonNull(Object obj, String name){
        if(obj == null || obj.toString().isEmpty()){
            throw new BodyGuardException(name + " can't be empty");
        }
    }

    private void authentication(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (BodyGuardException e){
            throw new BodyGuardException("Incorrect password");
        }catch (AuthenticationServiceException e){
            throw  new UserNotFoundException("Incorrect username");
        }
    }
}