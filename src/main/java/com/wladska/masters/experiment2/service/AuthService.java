package com.wladska.masters.experiment2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    // Method to authenticate a user and return a JWT token
    public String authenticate(String username, String password) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        // You would typically add JWT generation here, but for simplicity, let's just return a simple token
        // Assuming token generation or any other post-authentication logic here
        return "token-for-" + username;
    }
}