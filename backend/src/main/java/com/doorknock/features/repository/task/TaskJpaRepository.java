package com.doorknock.features.repository.task;

import com.doorknock.features.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<Task, UUID> {

    List<Task> findByUser_UserId(UUID userId);
}
