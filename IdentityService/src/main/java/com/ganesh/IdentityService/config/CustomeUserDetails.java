package com.ganesh.IdentityService.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ganesh.IdentityService.entity.UserCredential;
@Component
public class CustomeUserDetails implements UserDetails{
private String userName;
private String password;



    public CustomeUserDetails(UserCredential userCredential) {
    this.userName = userCredential.getName();
    this.password = userCredential.getPassword();
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
       return userName;
    }

 

}
