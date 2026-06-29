package com.doorknock.features.model.dtos.visit;
import java.time.Instant;

public record VisitStat(
    long totalDoorKnocked,
    Instant lastActive
) {
}
