package com.example.dailyNotesAPI.services;

import com.example.dailyNotesAPI.entities.Category;
import com.example.dailyNotesAPI.entities.Note;
import com.example.dailyNotesAPI.entities.NoteUpdateDTO;
import com.example.dailyNotesAPI.entities.User;
import com.example.dailyNotesAPI.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    //    create new note
    public Note createNewNote(Note note, Principal principal) {
        User user = userService.getAuthenticatedUser(principal);
        note.setCategory(categoryService.getCategoryById(note.getCategory().getId()));
        note.setUser(user);
        return noteRepository.save(note);
    }

    //    get all notes by auth user
    public List<Note> getAllNoteByAuthUser(Principal principal) {
        User authenticatedUser = userService.getAuthenticatedUser(principal);
        List<Note> notes = noteRepository.findAllNotesByUserId(authenticatedUser.getId());
        return notes;
    }

    //    get note by note id
    public Note getNoteByNoteId(Long id, Principal principal) {
        Note note;
        try {
            note = noteRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
        if (isNoteBelongsToAuthUser(principal, note)) {
            return note;
        }
        return null;
    }


    //    update note { Note : note }
    public Note updateNote(NoteUpdateDTO noteDTO, Principal principal) {
        Note updatedNote;
        try {
             updatedNote = noteRepository.findById(noteDTO.getNoteId()).get();
        }catch (Exception e){
            return null;
        }
        User user = userService.getAuthenticatedUser(principal);
        Category category = categoryService.getCategoryById(noteDTO.getCategoryId());
        Note note = new Note();
        note.setId(noteDTO.getNoteId());

        if (isNoteBelongsToAuthUser(principal, note)) {
            note.setTitle(noteDTO.getTitle());
            note.setCategory(category);
            note.setUser(user);
            note.setContent(noteDTO.getContent());
            return noteRepository.save(note);
        }
        return null;
    }

    //    delete note by id
    public Note deleteNoteById(Long id, Principal principal) {
        Note note;
        try {
            note = noteRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
        if (isNoteBelongsToAuthUser(principal, note)) {
            noteRepository.delete(note);
        }
        return note;
    }


    //    check is note belongs to authenticated user
    public boolean isNoteBelongsToAuthUser(Principal principal, Note note) {
        Note note1;
        User user = userService.getAuthenticatedUser(principal);
        try {
            note1 = noteRepository.findById(note.getId()).get();
        } catch (Exception e) {
            return false;
        }
        if (note1.getUser().getId() == user.getId()) {
            return true;
        }
        return false;
    }
}

