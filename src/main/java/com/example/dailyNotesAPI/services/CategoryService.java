package com.example.dailyNotesAPI.services;

import com.example.dailyNotesAPI.entities.Category;
import com.example.dailyNotesAPI.entities.User;
import com.example.dailyNotesAPI.entitiesDTO.CategoryDto;
import com.example.dailyNotesAPI.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    //    crate new category
    public Category createCategory(CategoryDto categoryDto, Principal principal) {
        Category category = categoryDtoToCategory(categoryDto);
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
    public Category updateCategoryById(CategoryDto categoryDto, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        Category category = this.categoryDtoToCategory(categoryDto);
        if (!isCategoryBelongsToAuthUser(principal, category)) {
            return null;
        }
        Category updatedCategory = (Category) categoryRepository.findById(category.getId()).get();
        updatedCategory.setName(category.getName());
        return categoryRepository.save(updatedCategory);
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
        } catch (Exception e) {
            return null;
        }
    }

    //    check if category belongs to authenticated user
    public boolean isCategoryBelongsToAuthUser(Principal principal, Category category) {
        User user = userService.getAuthenticatedUser(principal);
        Category categoryByIdAndUserId;
        try {
            categoryByIdAndUserId = categoryRepository.findCategoryByIdAndUserId(category.getId(), user.getId());
        } catch (Exception e) {
            return false;
        }
        if (categoryByIdAndUserId == null) {
            return false;
        }
        return true;
    }

    //    convert category to dto
    private Category categoryDtoToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setId(categoryDto.getId());
        return category;
    }
}
