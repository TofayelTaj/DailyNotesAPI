package com.example.dailyNotesAPI.security;

import com.example.dailyNotesAPI.entities.User;
import com.example.dailyNotesAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ApplicationUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        if(user == null){
            throw new  UsernameNotFoundException("User Not Found !");
        }
        ApplicationUserDetails userDetails = new ApplicationUserDetails(user);
        return userDetails;
    }
}
