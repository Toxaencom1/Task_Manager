package com.taxah.spring_dz5.service;

import com.taxah.spring_dz5.model.Task;
import com.taxah.spring_dz5.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return repository.findById(id);
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public List<Task> getTasksByStatus(Task.Status status) {
        return repository.findByStatus(status);
    }

//    public Task updateTask(Task updatedTask) {
//        return repository.save(updatedTask);
//    }
    public Task updateTask(Task updatedTask, Long id) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setDescription(updatedTask.getDescription());
            task.setLocalDateTime(LocalDateTime.now());
            task.setStatus(updatedTask.getStatus());
            return repository.save(task);
        } else {
            throw new IllegalArgumentException("Task not found with id: " + id);
        }
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }
}
