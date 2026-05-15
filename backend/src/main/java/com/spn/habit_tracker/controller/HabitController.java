package com.spn.habit_tracker.controller;

import com.spn.habit_tracker.model.Habit;
import com.spn.habit_tracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "*") 
public class HabitController {

    @Autowired
    private HabitRepository habitRepository;

    // Dohvati sve navike
    @GetMapping
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    // Dodaj novu naviku
    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return habitRepository.save(habit);
    }

    // Brisanje navike prema ID-u
    @DeleteMapping("/{id}")
    public String deleteHabit(@PathVariable Long id) {
        habitRepository.deleteById(id);
        return "Navika s ID-em " + id + " je uspješno obrisana.";
    }

    // Ažuriranje postojeće navike
    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit habitDetails) {
        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Navika nije pronađena pod ID-em: " + id));
        
        habit.setTitle(habitDetails.getTitle());
        habit.setDescription(habitDetails.getDescription());
        habit.setCategory(habitDetails.getCategory());
        
        return habitRepository.save(habit);
    }
}