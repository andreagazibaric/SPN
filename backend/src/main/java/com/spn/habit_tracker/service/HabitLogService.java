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
        // 1. Složena validacija: Provjera duplikata za isti dan
        if (habitLogRepository.existsByHabitAndDate(log.getHabit(), log.getDate())) {
            throw new IllegalArgumentException("Navika je već logirana za ovaj datum!");
        }

        // 2. Dodatna složena validacija: Ne dopuštamo logiranje u budućnosti
        if (log.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Nije moguće logirati naviku za datum u budućnosti!");
        }

        return habitLogRepository.save(log);
    }
}