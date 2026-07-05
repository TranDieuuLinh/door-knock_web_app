package com.doorknock.features.model.dtos;

import com.doorknock.features.common.enums.VolunteerRoles;

import java.time.Instant;
import java.util.UUID;

public record UserWithVisitStatsResponse(
        UUID userId,
        String name,
        String territory,
        String campaign,
        VolunteerRoles role,
        long totalDoorKnocked,
        Instant lastActive
) {
}
