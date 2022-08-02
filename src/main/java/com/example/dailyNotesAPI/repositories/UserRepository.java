package com.example.dailyNotesAPI.repositories;

import com.example.dailyNotesAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
}
