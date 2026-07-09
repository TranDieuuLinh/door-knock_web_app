package com.doorknock.features.repository.household;

import com.doorknock.features.model.dtos.household.HouseholdDetailResponse;
import com.doorknock.features.model.entities.Household;

import java.util.Optional;
import java.util.UUID;

public interface HouseholdRepository {

    Optional<Household> findHouseholdDetail(UUID householdId);

    Optional<Household> findHouseholdByTaskId(UUID taskId);
}
