package com.taxah.spring_dz5.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;

    @Column(name = "LOCALDATETIME", nullable = false)
    private LocalDateTime localDateTime = LocalDateTime.now();
    @Column
    @Enumerated(EnumType.STRING)
    private Status status = Status.CREATE;
    public enum Status{
        CREATE,
        PROCESSING,
        FINISHED
    }
}



