package com.example.dailyNotesAPI.controllers;

import com.example.dailyNotesAPI.entities.Note;
import com.example.dailyNotesAPI.entities.NoteUpdateDTO;
import com.example.dailyNotesAPI.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

//  create new note
    @PostMapping("/notes")
    public ResponseEntity<Note> createNewNote(@RequestBody  Note note, Principal principal){
        Note createdNote = noteService.createNewNote(note, principal);
        if(createdNote == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(createdNote, HttpStatus.OK);
    }


//    get all notes by authenticated user
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotesByAuthUser(Principal principal){
        List<Note> notes = noteService.getAllNoteByAuthUser(principal);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }


//    get note by NoteId and auth User
    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteByIdAndAuthUser(@PathVariable Long id, Principal principal){
        Note note = noteService.getNoteByNoteId(id, principal);
        if(note == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(note, HttpStatus.FOUND);
    }

//    update Note by NoteId and auth User

    @PutMapping("/notes")
    public ResponseEntity<Note>  updateNoteById(@RequestBody NoteUpdateDTO updateDTO, Principal principal){

        Note updateNote = noteService.updateNote(updateDTO, principal);
        if(updateNote == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateNote, HttpStatus.OK);
    }

//    delete note by noteId and auth user
    @DeleteMapping("/notes/{id}")
    public ResponseEntity deleteNoteById(@PathVariable Long id, Principal principal){
        Note note = noteService.deleteNoteById(id, principal);
        if(note == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
