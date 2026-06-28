package com.doorknock.features.service.visit;

import com.doorknock.features.model.dtos.visit.VisitStats;
import com.doorknock.features.repository.visit.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public VisitStats getVisitStatsByUserId(UUID userId) {
        long totalDoorKnocked = visitRepository.countVisitByUserId(userId);
        var lastActive = visitRepository.findLatestVisitedTimeByUserId(userId).orElse(null);
        return new VisitStats(totalDoorKnocked, lastActive);
    }
}
