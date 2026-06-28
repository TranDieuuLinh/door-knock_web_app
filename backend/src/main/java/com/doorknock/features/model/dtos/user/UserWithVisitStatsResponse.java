package com.doorknock.features.model.dtos.user;

import com.doorknock.features.model.dtos.visit.VisitStats;

public record UserWithVisitStatsResponse(
        UserResponse user,
        VisitStats visitStats
) {
}
