package com.spn.habit_tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "habits")
@Getter
@Setter
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    // Povezivanje s kategorijom (Many habits belong to one category)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    
}