package com.example.dailyNotesAPI.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Email
    private String email;
    private String password;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Category> category;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Note> notes;


}
