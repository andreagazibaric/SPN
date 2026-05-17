package com.spn.habit_tracker.controller;

import com.spn.habit_tracker.model.HabitLog;
import com.spn.habit_tracker.service.HabitLogService;
import com.spn.habit_tracker.repository.HabitLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "*")
public class HabitLogController {

    @Autowired
    private HabitLogService habitLogService;

    @Autowired
    private HabitLogRepository habitLogRepository;

    @GetMapping
    public List<HabitLog> getAllLogs() {
        return habitLogRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody HabitLog log) {
        try {
            HabitLog savedLog = habitLogService.saveLog(log);
            return ResponseEntity.ok(savedLog);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteLog(@PathVariable Long id) {
        habitLogRepository.deleteById(id);
        return "Log obrisan!";
    }

    @PutMapping("/{id}")
    public HabitLog updateLog(@PathVariable Long id, @RequestBody HabitLog updatedLog) {

        HabitLog log = habitLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log nije pronađen"));

        log.setCompleted(updatedLog.isCompleted());
        log.setDate(updatedLog.getDate());

        return habitLogRepository.save(log);
    }

}