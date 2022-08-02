package com.example.dailyNotesAPI.services;

import com.example.dailyNotesAPI.entities.User;
import com.example.dailyNotesAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
           user  = userRepository.save(user);
        }catch(Exception ex){
            return null;
        }
        return user;
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

}
