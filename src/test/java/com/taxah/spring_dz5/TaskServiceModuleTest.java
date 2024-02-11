package com.taxah.spring_dz5;

import com.taxah.spring_dz5.exceptions.ResourceNotFoundException;
import com.taxah.spring_dz5.model.Status;
import com.taxah.spring_dz5.model.Task;
import com.taxah.spring_dz5.repository.TaskRepository;
import com.taxah.spring_dz5.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceModuleTest {
    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    Task task;
    Long id;

    @BeforeEach
    void setup() {
        id = 1L;
        task = new Task();
        task.setId(id);
        task.setDescription("New Task");
    }

    @Test
    void getAllTasksTest() {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        given(repository.findAll()).willReturn(tasks);
        // Action
        List<Task> result = service.getAllTasks();
        // Assert
        verify(repository).findAll();
        assertEquals(tasks, result);
    }

    @Test
    void getTaskTest() {
        Optional<Task> optionalTask = Optional.of(task);
        given(service.getTask(id)).willReturn(optionalTask);

        Optional<Task> res = service.getTask(id);

        verify(repository).findById(id);
        assertEquals(optionalTask, res);
    }

    @Test
    void addTaskTest() {
        given(repository.save(task)).willReturn(task);

        service.addTask(task);

        verify(repository).save(task);
    }

    @Test
    void updateTaskPositiveTest() {
        Optional<Task> optionalTask = Optional.of(task);
        given(repository.findById(id)).willReturn(optionalTask);
        given(repository.save(task)).willReturn(task);

        Task res = service.updateTask(task);

        verify(repository).findById(id);
        verify(repository).save(task);
        assertEquals(task, res);
    }

    @Test
    void updateTaskNegativeTest() {
        Optional<Task> optionalTask = Optional.empty();
        given(repository.findById(id)).willReturn(optionalTask);

        assertThrows(ResourceNotFoundException.class, () -> service.updateTask(task));
        verify(repository).findById(id);
    }

    @Test
    void deleteTaskPositiveTest() {
        Optional<Task> optionalTask = Optional.of(task);
        given(repository.findById(id)).willReturn(optionalTask);

        service.deleteTask(id);

        verify(repository).findById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void deleteTaskNegativeTest() {
        Optional<Task> optionalTask = Optional.empty();
        given(repository.findById(id)).willReturn(optionalTask);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteTask(id));
        verify(repository).findById(id);
    }

    @Test
    void findByStatusTest() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        given(repository.findByStatus(Status.CREATE)).willReturn(tasks);

        List<Task> res = service.findByStatus(Status.CREATE);

        verify(repository).findByStatus(Status.CREATE);
        assertEquals(tasks, res);
    }
}
