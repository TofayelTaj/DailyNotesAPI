package com.example.dailyNotesAPI.repositories;

import com.example.dailyNotesAPI.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "select * from note where user_id =:id", nativeQuery = true)
    List<Note> findAllNotesByUserId(@Param("id") Long id);

}
