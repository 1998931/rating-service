package com.ganesh.IdentityService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ganesh.IdentityService.dto.AuthRequest;
import com.ganesh.IdentityService.entity.UserCredential;
import com.ganesh.IdentityService.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
@Autowired
    private AuthenticationManager authenticationManager;
@PostMapping("/register")
    public String addUser(@RequestBody UserCredential user){
        return authService.saveUser(user);
    }
@PostMapping("/token")
    public String getToken(@RequestBody AuthRequest user){
      Authentication authenticate=   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
       if(authenticate.isAuthenticated()) {return authService.generateToken(user.getUsername());}
       else{
        throw new RuntimeException("User is invalid ");

       }
    }
  
    @GetMapping("/validate/{token}")
    public String validateToken(@PathVariable String token ){
         authService.validateToken(token);
         return "Token is valid";
    }
}
