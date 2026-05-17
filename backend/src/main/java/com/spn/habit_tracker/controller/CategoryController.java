package com.spn.habit_tracker.controller;

import com.spn.habit_tracker.model.Category;
import com.spn.habit_tracker.repository.CategoryRepository;
import com.spn.habit_tracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*") 
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private HabitRepository habitRepository;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {

        if (!habitRepository.findAll().stream()
                .filter(h -> h.getCategory() != null)
                .anyMatch(h -> h.getCategory().getId().equals(id))) {

            categoryRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }

        return ResponseEntity
                .badRequest()
                .body("Ne možeš obrisati kategoriju koja sadrži navike!");
    }
}