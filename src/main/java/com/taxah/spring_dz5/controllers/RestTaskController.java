package com.taxah.spring_dz5.controllers;

import com.taxah.spring_dz5.aspects.TrackUserAction;
import com.taxah.spring_dz5.exceptions.ResourceNotFoundException;
import com.taxah.spring_dz5.model.Status;
import com.taxah.spring_dz5.model.Task;
import com.taxah.spring_dz5.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestTaskController Class
 * <p>
 * Example Usage:
 * Accessing all tasks: GET request to "/api"
 * Accessing task by Id: GET request to "/api/{id}"
 * Adding a task: POST request to "/api"
 * Updating a task: PUT request to "/api/{id}"
 * Deleting a task: DELETE request to "/api/{id}"
 * Retrieving tasks by status: GET request to "/api/filter/{status}" Status must be UPPER CASE
 * <p>
 * Dependencies:
 * - TaskService: The service for handling task-related business logic.
 * <p>
 * Annotations:
 * - @RestController: Indicates that this class is a RESTful controller.
 * - @AllArgsConstructor: Lombok annotation to generate a constructor with all required fields.
 */
@AllArgsConstructor
@RestController
public class RestTaskController {
    private TaskService service;

    /**
     * Get All Tasks
     * <p>
     * Handles GET requests to "/api" and retrieves all tasks.
     *
     * @return List<Task>: A list of all tasks.
     */
    @TrackUserAction
    @GetMapping("/api")
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    /**
     * Get Task by Id
     * <p>
     * Handles GET requests to "/api/{id}" and retrieves task by Id.
     *
     * @return Task: or throw a ResourceNotFoundException.
     */
    @TrackUserAction
    @GetMapping("/api/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return service.getTask(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    /**
     * Add Task
     * <p>
     * Handles POST requests to "/api" and adds a new task.
     *
     * @param task Task: The task to add.
     * @return Task: The added task.
     */
    @TrackUserAction
    @PostMapping("/api")
    public Task addtask(@RequestBody Task task) {
        return service.addTask(task);
    }

    /**
     * Update Task
     * <p>
     * Handles PUT requests to "/api" and updates a task from body.
     *
     * @param task Task: The updated task.
     * @return Task: The updated task.
     */
    @TrackUserAction
    @PutMapping("/api")
    public Task updateTask(@RequestBody Task task) {
        return service.updateTask(task);
    }

    /**
     * Delete Task
     * <p>
     * Handles DELETE requests to "/api/{id}" and deletes a task by ID.
     *
     * @param id Long: The ID of the task to delete.
     */
    @TrackUserAction
    @DeleteMapping("/api/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }

    /**
     * Get Task by Status
     * <p>
     * Handles GET requests to "/api/{status}" and retrieves tasks by status.
     *
     * @param status Status: The status of tasks to retrieve.
     * @return List<Task>: A list of tasks with the specified status.
     */
    @TrackUserAction
    @GetMapping("/api/filter/{status}")
    public List<Task> getTaskByStatus(@PathVariable Status status) {
        return service.findByStatus(status);
    }
}
