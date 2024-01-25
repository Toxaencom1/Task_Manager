package com.taxah.spring_dz5.repository;

import com.taxah.spring_dz5.model.Status;
import com.taxah.spring_dz5.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
}
