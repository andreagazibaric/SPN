package com.spn.habit_tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "habit_logs")
@Getter
@Setter
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;
}