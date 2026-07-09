package com.doorknock.features.service.household;

import com.doorknock.features.model.dtos.household.HouseholdDetailResponse;

import java.util.UUID;

public interface HouseholdService {
    HouseholdDetailResponse getHouseHoldDetailResponse(UUID taskId);
    HouseholdDetailResponse getHouseholdDetail(UUID householdId);
}
