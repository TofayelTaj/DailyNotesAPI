package com.example.dailyNotesAPI.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {

    private String title;
    private String content;
    private Long categoryId;
    private Long noteId;

}
