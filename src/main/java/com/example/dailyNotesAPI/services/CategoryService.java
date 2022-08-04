package com.example.dailyNotesAPI.services;

import com.example.dailyNotesAPI.entities.Category;
import com.example.dailyNotesAPI.entities.User;
import com.example.dailyNotesAPI.repositories.CategoryRepository;
import org.hibernate.type.PrimitiveCharacterArrayClobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.security.Principal;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    //    crate new category
    public Category createCategory(Category category, Principal principal) {
        category.setUser(userService.getAuthenticatedUser(principal));
        try {
            category = categoryRepository.save(category);
        } catch (Exception e) {
            return null;
        }
        return category;
    }

    //    get all categories by auth user
    public List<Category> getAllCategoriesByAuthUser(Principal principal) {
        User user = userService.getAuthenticatedUser(principal);
        List<Category> categories = categoryRepository.getCategoriesByAuthUser(user.getId());
        return categories;
    }


    //    update category by id and auth user
    public Category updateCategoryById(Category category, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        if (!isCategoryBelongsToAuthUser(user, category)) {
            return null;
        }
        category.setUser(user);
        return categoryRepository.save(category);
    }

    //    delete category by id and auth user
    public Category deleteCategoryById(Long id, Principal principal) {
        User user = userService.getAuthenticatedUser(principal);
        Category category = categoryRepository.findCategoryByIdAndUserId(id, user.getId());
        if (category == null) {
            return null;
        }
        categoryRepository.delete(category);
        return category;
    }

    //    get categoryById
    public Category getCategoryById(Long id) {
        try {
            return categoryRepository.findById(id).get();
        }catch (Exception e){
            return null;
        }
    }

    //    check if category belongs to authenticated user
    public boolean isCategoryBelongsToAuthUser(User user, Category category) {
        Category categoryByIdAndUserId = categoryRepository.findCategoryByIdAndUserId(category.getId(), user.getId());
        if (categoryByIdAndUserId == null) {
            return false;
        }
        return true;
    }

}
