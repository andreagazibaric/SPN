package com.spn.habit_tracker.controller;

import com.spn.habit_tracker.model.Habit;
import com.spn.habit_tracker.repository.HabitLogRepository;
import com.spn.habit_tracker.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@CrossOrigin(origins = "*") 
public class HabitController {

    @Autowired
    private HabitRepository habitRepository;
    @Autowired
    private HabitLogRepository logRepository;

    @GetMapping
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        return habitRepository.save(habit);
    }

    @DeleteMapping("/{id}")
    public String deleteHabit(@PathVariable Long id) {
        habitRepository.deleteById(id);
        return "Navika s ID-em " + id + " je uspješno obrisana.";
    }

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