package com.doorknock.features.service.visit;

import com.doorknock.features.model.dtos.visit.VisitStat;

import java.util.UUID;

public interface VisitService {

    VisitStat getVisitStatsByUserId(UUID userId);
}
