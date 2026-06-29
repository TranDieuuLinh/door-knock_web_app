package com.doorknock.features.mapper;

import com.doorknock.features.model.dtos.user.UserResponse;
import com.doorknock.features.model.dtos.user.UserWithVisitStatsResponse;
import com.doorknock.features.model.dtos.visit.VisitStat;
import com.doorknock.features.model.entities.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getTerritory(),
                user.getCampaign(),
                user.getRole()
        );
    }

    public static UserWithVisitStatsResponse toResponseWithVisitStats(User user, VisitStat visitStat) {
        long totalDoorKnocked = visitStat == null ? 0L : visitStat.totalDoorKnocked();
        var lastActive = visitStat == null ? null : visitStat.lastActive();

        return new UserWithVisitStatsResponse(
                user.getUserId(),
                user.getName(),
                user.getTerritory(),
                user.getCampaign(),
                user.getRole(),
                totalDoorKnocked,
                lastActive
        );
    }
}
