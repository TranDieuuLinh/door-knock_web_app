package com.doorknock.features.repository.visit;

import com.doorknock.features.model.dtos.visit.VisitStat;
import com.doorknock.features.model.entities.Visit;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class VisitRepositoryImpl implements VisitRepository {

    private final VisitJpaRepository jpaRepository;

    public VisitRepositoryImpl(VisitJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Visit save(Visit visit) {
        return jpaRepository.save(visit);
    }

    @Override
    public Optional<Visit> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public List<Visit> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(Visit visit) {
        jpaRepository.delete(visit);
    }

    @Override
    public long countVisitByUserId(UUID userId) {
        return jpaRepository.countByUser_UserId(userId);
    }

    @Override
    public Optional<Instant> findLatestVisitedTimeByUserId(UUID userId) {
        return jpaRepository.findTopByUser_UserIdOrderByVisitedAtDesc(userId)
                .map(Visit::getVisitedAt);
    }

    @Override
    public Map<UUID, VisitStat> getStatsByUserIds(List<UUID> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            return Map.of();
        }

        return jpaRepository.findByUser_UserIdIn(userIds).stream()
                .collect(Collectors.groupingBy(
                        visit -> visit.getUser().getUserId(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                this::toVisitStats
                        )
                ));
    }

    private VisitStat toVisitStats(List<Visit> visits) {
        Instant lastActive = visits.stream()
                .map(Visit::getVisitedAt)
                .max(Instant::compareTo)
                .orElse(null);
        return new VisitStat(visits.size(), lastActive);
    }
}
