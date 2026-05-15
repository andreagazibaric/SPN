package com.spn.habit_tracker.controller;

import com.spn.habit_tracker.model.HabitLog;
import com.spn.habit_tracker.repository.HabitLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*") 
public class HabitLogController {

    @Autowired
    private HabitLogRepository habitLogRepository;

    @GetMapping
    public List<HabitLog> getAllLogs() {
        return habitLogRepository.findAll();
    }

    @PostMapping
    public HabitLog createLog(@RequestBody HabitLog log) {
        return habitLogRepository.save(log);
    }

    @DeleteMapping("/{id}")
    public String deleteLog(@PathVariable Long id) {
        habitLogRepository.deleteById(id);
        return "Log obrisan!";
    }
}