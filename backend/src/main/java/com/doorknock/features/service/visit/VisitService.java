package com.doorknock.features.service.visit;

import com.doorknock.features.model.dtos.visit.VisitStats;

import java.util.UUID;

public interface VisitService {

    VisitStats getVisitStatsByUserId(UUID userId);
}
