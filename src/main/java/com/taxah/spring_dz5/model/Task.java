package com.taxah.spring_dz5.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

/**
 * Task Class
 * <p>
 * This class represents a task entity in a task management system.
 * It includes attributes such as ID, description, creation timestamp, and status.
 * <p>
 * Dependencies:
 * - @Data: Lombok annotation to automatically generate getter, setter, toString, etc.
 * - @Entity: Indicates that this class is a JPA entity.
 * - @Table: Specifies the table name for the entity in the database.
 */
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
    private Status status;
}



