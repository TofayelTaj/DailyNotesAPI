package com.example.dailyNotesAPI.repositories;

import com.example.dailyNotesAPI.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM category where user_id = :id", nativeQuery = true)
    List<Category>  getCategoriesByAuthUser(@Param("id") Long id);

    @Query(value = "SELECT * FROM category where id = :categoryId and user_id = :userId", nativeQuery = true)
    Category findCategoryByIdAndUserId(@Param("categoryId") Long categoryId, @Param("userId") Long userId);
}
