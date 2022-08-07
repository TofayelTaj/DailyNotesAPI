package com.example.dailyNotesAPI.controllers;

import com.example.dailyNotesAPI.entities.JwtRequest;
import com.example.dailyNotesAPI.entities.JwtResponse;
import com.example.dailyNotesAPI.security.ApplicationUserDetailsServiceImpl;
import com.example.dailyNotesAPI.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApplicationUserDetailsServiceImpl applicationUserDetails;


    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
                            jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new Exception("Invalid user name or password");
        }

        final UserDetails userDetails = applicationUserDetails.loadUserByUsername(jwtRequest.getUserName());
        final String token = jwtUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
