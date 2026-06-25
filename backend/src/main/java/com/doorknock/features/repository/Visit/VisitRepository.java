package com.doorknock.features.repository.Visit;

import com.doorknock.features.model.entities.Visit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VisitRepository {

    Visit save(Visit visit);

    Optional<Visit> findById(UUID id);

    List<Visit> findAll();

    void delete(Visit visit);
}
