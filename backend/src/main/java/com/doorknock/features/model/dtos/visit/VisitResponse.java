package com.doorknock.features.model.dtos.visit;

import com.doorknock.features.common.enums.VisitStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record VisitResponse(
        UUID visitId,
        UUID userId,
        VisitStatus status,
        LocalDateTime visitedAt
) {
}
