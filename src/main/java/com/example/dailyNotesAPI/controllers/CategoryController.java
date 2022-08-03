package com.example.dailyNotesAPI.controllers;

import com.example.dailyNotesAPI.entities.Category;
import com.example.dailyNotesAPI.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

//    create new categories
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category,
                                                   BindingResult result,
                                                   Principal principal){
        if(result.hasErrors()){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        category = categoryService.createCategory(category, principal);
        if(category == null ){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

//    get all categories by auth user
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategoriesByAuthUser(Principal principal){
        List<Category> categories = categoryService.getAllCategoriesByAuthUser(principal);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

//    update category by categoryId and auth user
    @PutMapping("/categories")
    public ResponseEntity<Category> updateCategoryById(@RequestBody Category category, Principal principal){
        category = categoryService.updateCategoryById(category, principal);
        if(category == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

//    delete category by categoryId and auth user
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id, Principal principal){
        Category category = categoryService.deleteCategoryById(id, principal);
        if(category == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
    }


}
