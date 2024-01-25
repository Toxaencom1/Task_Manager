package com.taxah.spring_dz5.repository;

import com.taxah.spring_dz5.model.Status;
import com.taxah.spring_dz5.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TaskRepository Interface
 * <p>
 * This interface defines methods for accessing and managing task entities in the database.
 * It extends JpaRepository for basic CRUD operations and includes a custom query method
 * for finding tasks by status.
 * <p>
 * Dependencies:
 * - JpaRepository: Interface providing basic CRUD operations for entities.
 * <p>
 * Methods:
 * - findByStatus(Status status): Finds tasks by their status.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     * Find Tasks by Status
     *
     * Retrieves tasks from the database that match the specified status.
     *
     * @param status Status: The status of tasks to find.
     * @return List<Task>: A list of tasks with the specified status.
     */
    List<Task> findByStatus(Status status);
}
