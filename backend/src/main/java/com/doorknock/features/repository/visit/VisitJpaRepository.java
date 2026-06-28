package com.doorknock.features.repository.visit;

import com.doorknock.features.model.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VisitJpaRepository extends JpaRepository<Visit, UUID> {

    long countByUserId(UUID userId);

    Optional<Visit> findTopByUserIdOrderByVisitedAtDesc(UUID userId);

    List<Visit> findByUserIdIn(Collection<UUID> userIds);
}
