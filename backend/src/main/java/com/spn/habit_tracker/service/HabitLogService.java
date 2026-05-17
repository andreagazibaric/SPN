package com.spn.habit_tracker.service;

import com.spn.habit_tracker.model.HabitLog;
import com.spn.habit_tracker.repository.HabitLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HabitLogService {

    @Autowired
    private HabitLogRepository habitLogRepository;

    public HabitLog saveLog(HabitLog log) {
        if (habitLogRepository.existsByHabitAndDate(log.getHabit(), log.getDate())) {
            throw new IllegalArgumentException("Navika je već logirana za ovaj datum!");
        }

        return habitLogRepository.save(log);
    }
}