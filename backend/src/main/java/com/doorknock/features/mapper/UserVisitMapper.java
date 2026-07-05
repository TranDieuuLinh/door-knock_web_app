package com.doorknock.features.mapper;

import com.doorknock.features.model.dtos.UserWithVisitStatsResponse;
import com.doorknock.features.model.dtos.visit.VisitStat;
import com.doorknock.features.model.entities.User;

public final class UserVisitMapper {

    private UserVisitMapper() {
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
