package com.spn.habit_tracker.repository;

import com.spn.habit_tracker.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
}