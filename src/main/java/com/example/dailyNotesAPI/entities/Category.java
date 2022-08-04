package com.example.dailyNotesAPI.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Category Name can't be Empty or Null.")
    private String name;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private User user;
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<Note> notes;
}
