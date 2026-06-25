package com.doorknock.features.repository.Visit;

import com.doorknock.features.model.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VisitJpaRepository extends JpaRepository<Visit, UUID> {
}
