package com.doorknock.features.repository.visit;

import com.doorknock.features.model.dtos.visit.VisitStat;
import com.doorknock.features.model.entities.Visit;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface VisitRepository {

    Visit save(Visit visit);

    Optional<Visit> findById(UUID id);

    List<Visit> findAll();

    void delete(Visit visit);

    long countVisitByUserId(UUID userId);

    Optional<Instant> findLatestVisitedTimeByUserId(UUID userId);
    Map<UUID, VisitStat> getStatsByUserIds(List<UUID> userIds);
}
