package com.doorknock.features.repository.household;

import com.doorknock.features.model.dtos.household.HouseholdDetailResponse;
import com.doorknock.features.model.entities.Household;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class HouseholdRepositoryImpl implements HouseholdRepository {

    private final HouseholdJpaRepository jpaRepository;

    public HouseholdRepositoryImpl(HouseholdJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Household> findHouseholdDetail(UUID householdId){
        return jpaRepository.findById(householdId);
    }

    @Override
    public Optional<Household> findHouseholdByTaskId(UUID taskId) {
        return jpaRepository.findByTasks_TaskId(taskId);
    }
}
