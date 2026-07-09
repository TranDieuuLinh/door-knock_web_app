package com.doorknock.features.repository.household;

import com.doorknock.features.model.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HouseholdJpaRepository extends JpaRepository<Household, UUID> {

    Optional<Household> findByTasks_TaskId(UUID taskId);
}
