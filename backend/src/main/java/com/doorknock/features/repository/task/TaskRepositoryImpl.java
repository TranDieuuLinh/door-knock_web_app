package com.doorknock.features.repository.task;

import com.doorknock.features.model.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository jpaRepository;

    public TaskRepositoryImpl(TaskJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Task save(Task task) {
        return jpaRepository.save(task);
    }

    @Override
    public Optional<Task> findByTaskId(UUID taskId) {
        return jpaRepository.findById(taskId);
    }

    @Override
    public List<Task> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(Task task) {
        jpaRepository.delete(task);
    }

    @Override
    public List<Task> findTaskByUserID(UUID userId) {
        return jpaRepository.findByUser_UserId(userId);
    }
}
