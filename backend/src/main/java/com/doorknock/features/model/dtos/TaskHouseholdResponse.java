package com.doorknock.features.model.dtos;

import com.doorknock.features.common.enums.TaskStatus;

import java.util.UUID;

public record TaskHouseholdResponse(
        UUID taskId,
        TaskStatus taskStatus,
        UUID householdId,
        String address,
        String suburb,
        String postcode,
        String familyName
) {
}
