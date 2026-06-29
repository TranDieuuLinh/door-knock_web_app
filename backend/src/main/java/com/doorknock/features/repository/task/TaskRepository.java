package com.doorknock.features.repository.task;

import com.doorknock.features.model.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findById(UUID id);

    List<Task> findAll();

    void delete(Task task);
}
