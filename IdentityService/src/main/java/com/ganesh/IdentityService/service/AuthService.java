package com.ganesh.IdentityService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ganesh.IdentityService.entity.UserCredential;
import com.ganesh.IdentityService.repository.UserCredentialRepository;

@Service
public class AuthService {

@Autowired
private UserCredentialRepository userCredentialRepository;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private JwtService jwtService;
public String saveUser(UserCredential userCredential){
    userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
    userCredentialRepository.save(userCredential);
    return "save user to our system";
}

public String generateToken(String userName){
    return jwtService.generateToken(userName);

}

public void validateToken(String token){
    jwtService.validateToken(token);
}
}
