package com.spn.habit_tracker.repository;

import com.spn.habit_tracker.model.HabitLog;
import com.spn.habit_tracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    // Postoji li već log za zadanu naviku i točan datum
    boolean existsByHabitAndDate(Habit habit, LocalDate date);
}