package com.doorknock.features.model.dtos.visit;

import java.time.Instant;

public record VisitStats(
        long totalDoorKnocked,
        Instant lastActive
) {
    public static VisitStats empty() {
        return new VisitStats(0L, null);
    }
}
