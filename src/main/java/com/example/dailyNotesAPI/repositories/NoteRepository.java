package com.example.dailyNotesAPI.repositories;

import com.example.dailyNotesAPI.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "select * from note where user_id =:id", nativeQuery = true)
    List<Note> findAllNotesByUserId(@Param("id") Long id);

    List<Note> findNotesByCategory_Id(Long category_Id);

    List<Note> findNotesByUser_IdAndTitleLike(Long user_id, String title);
    List<Note> findNotesByUser_IdAndContentLike(Long user_id, String content);

//    List<Note> findNotesByUser_IdAndCategory_IdAndTitleLikeOrContentLike(Long user_id, Long category_id, String query, String query1);
    @Query(value = "select * from note where user_id = :user_id and category_id = :category_id and (title like :query or content like :query1)", nativeQuery = true)
    List<Note> findNotesByUser_IdAndCategory_IdAndTitleLikeOrContentLike(@Param("user_id") Long user_id,
                                                                         @Param("category_id") Long category_id,
                                                                         @Param("query") String query,
                                                                         @Param("query1") String query1);
}
