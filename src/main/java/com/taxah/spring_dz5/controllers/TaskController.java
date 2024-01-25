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

    @GetMapping("/")
    public String hello() {
        return "index";
    }


    @GetMapping("/tasks")
    public String showAllTasks(Model model) {
        List<Task> tasks = service.getAllTasks();
        model.addAttribute(TASKS_ATTRIBUTE, tasks);
        return TASKS;
    }

    @PostMapping("/tasks")
    public String addTask(Task task) {
        service.addTask(task);
        return R_TASKS;
    }

    @RequestMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        service.deleteTask(id);
        return R_TASKS;
    }

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

    @PutMapping("/task-update/{id}")
    public String updateTask(@PathVariable Long id, Task task) {
        service.updateTask(task, id);
        return R_TASKS;
    }

    @RequestMapping("/findByStatus")
    public String findByStatus(@RequestParam("state") Status status, Model model) {
        List<Task> tasks = service.findByStatus(status);
        model.addAttribute(TASKS_ATTRIBUTE, tasks);
        return TASKS;
    }
}
