package com.example.dailyNotesAPI.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 3, max = 15, message = "name must be between 3 to 15 character.")
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    @Size(min = 5, max = 60, message = "password must be between 5 to 60 character.")
    private String password;


}
