package com.example.dailyNotesAPI.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteUpdateDTO {

    private String title;
    private String content;
    private Long categoryId;
    private Long noteId;

}
