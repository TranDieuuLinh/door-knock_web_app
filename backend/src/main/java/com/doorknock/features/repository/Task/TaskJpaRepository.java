package com.doorknock.features.repository.Task;

import com.doorknock.features.model.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<Task, UUID> {
}
