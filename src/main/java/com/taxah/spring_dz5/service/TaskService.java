package com.taxah.spring_dz5.service;

import com.taxah.spring_dz5.exceptions.ResourceNotFoundException;
import com.taxah.spring_dz5.model.Status;
import com.taxah.spring_dz5.model.Task;
import com.taxah.spring_dz5.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * TaskService Class
 * <p>
 * This class provides business logic for managing tasks in a task management system.
 * It includes methods for retrieving, adding, updating, deleting tasks, and finding tasks by status.
 */
@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    /**
     * Get All Tasks
     * <p>
     * Retrieves all tasks from the database.
     *
     * @return List<Task>: A list of all tasks.
     */
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    /**
     * Get Task by ID
     * <p>
     * Retrieves a task by its ID from the database.
     *
     * @param id Long: The ID of the task to retrieve.
     * @return Optional<Task>: An optional containing the task, or empty if not found.
     */
    public Optional<Task> getTask(Long id) {
        return repository.findById(id);
    }

    /**
     * Add Task
     * <p>
     * Adds a new task to the database.
     *
     * @param task Task: The task to add.
     * @return Task: The added task.
     */
    public Task addTask(Task task) {
        return repository.save(task);
    }

    /**
     * Find Tasks by Status
     * <p>
     * Finds tasks by their status.
     *
     * @param status Status: The status of tasks to find.
     * @return List<Task>: A list of tasks with the specified status.
     */
    public List<Task> findByStatus(Status status) {
        return repository.findByStatus(status);
    }

    /**
     * Update Task
     * <p>
     * Updates an existing task in the database.
     *
     * @param updatedTask Task: The updated task.
     * @param id          Long: The ID of the task to update.
     * @return Task: The updated task.
     * @throws ResourceNotFoundException if the task with the given ID is not found.
     */
    public Task updateTask(Task updatedTask, Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(updatedTask.getDescription());
            task.setLocalDateTime(LocalDateTime.now());
            task.setStatus(updatedTask.getStatus());
            return repository.save(task);
        } else {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
    }

    /**
     * Delete Task
     * <p>
     * Deletes a task by its ID from the database.
     *
     * @param id Long: The ID of the task to delete.
     */
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
