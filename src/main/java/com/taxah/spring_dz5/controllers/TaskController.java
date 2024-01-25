package com.taxah.spring_dz5.controllers;

import com.taxah.spring_dz5.model.Status;
import com.taxah.spring_dz5.model.Task;
import com.taxah.spring_dz5.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
@RequestMapping
public class TaskController {
    private static final String TASKS = "tasks";
    private static final String TASKS_ATTRIBUTE = "tasks";
    private static final String R_TASKS = "redirect:/tasks";


    private final TaskService service;

    /**
     * Home Page
     * <p>
     * Handles requests to the root ("/") and returns the home page.
     *
     * @return String: The view name ("index") for the home page.
     */
    @GetMapping("/")
    public String hello() {
        return "index";
    }

    /**
     * Show All Tasks
     * <p>
     * Handles requests to "/tasks" and displays all tasks.
     *
     * @param model Model: The Spring MVC model for passing data to the view.
     * @return String: The view name for displaying tasks.
     */
    @GetMapping("/tasks")
    public String showAllTasks(Model model) {
        List<Task> tasks = service.getAllTasks();
        model.addAttribute(TASKS_ATTRIBUTE, tasks);
        return TASKS;
    }

    /**
     * Add Task
     * <p>
     * Handles POST requests to "/tasks" and adds a new task.
     *
     * @param task Task: The task to be added.
     * @return String: A redirection to the "/tasks" endpoint after adding the task.
     */
    @PostMapping("/tasks")
    public String addTask(Task task) {
        service.addTask(task);
        return R_TASKS;
    }

    /**
     * Delete Task
     * <p>
     * Handles GET requests to "/task-delete/{id}" and deletes a task by ID.
     *
     * @param id Long: The ID of the task to be deleted.
     * @return String: A redirection to the "/tasks" endpoint after deleting the task.
     */
    @RequestMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        service.deleteTask(id);
        return R_TASKS;
    }

    /**
     * Find Task by ID
     * <p>
     * Handles GET requests to "/task-update/{id}" and finds a task by ID for updating.
     *
     * @param id    Long: The ID of the task to find.
     * @param model Model: The Spring MVC model for passing data to the view.
     * @return String: The view name ("update") for updating the task.
     */
    @GetMapping("/task-update/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Optional<Task> optionalTask = service.getTask(id);
        Task task = null;
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
        }
        model.addAttribute("task", task);
        return "update";
    }

    /**
     * Update Task
     * <p>
     * Handles PUT requests to "/task-update/{id}" and updates a task by ID.
     *
     * @param id   Long: The ID of the task to be updated.
     * @param task Task: The updated task.
     * @return String: A redirection to the "/tasks" endpoint after updating the task.
     */
    @PutMapping("/task-update/{id}")
    public String updateTask(@PathVariable Long id, Task task) {
        service.updateTask(task, id);
        return R_TASKS;
    }

    /**
     * Find Tasks by Status
     * <p>
     * Handles requests to "/findByStatus" and finds tasks by status.
     *
     * @param status Status: The status of tasks to find.
     * @param model  Model: The Spring MVC model for passing data to the view.
     * @return String: The view name for displaying tasks.
     */
    @RequestMapping("/findByStatus")
    public String findByStatus(@RequestParam("state") Status status, Model model) {
        List<Task> tasks = service.findByStatus(status);
        model.addAttribute(TASKS_ATTRIBUTE, tasks);
        return TASKS;
    }
}
