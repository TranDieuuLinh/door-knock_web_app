package com.doorknock.features.repository.Visit;

import com.doorknock.features.model.entities.Visit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Visit> findById(UUID id) {return jpaRepository.findById(id);}

    @Override
    public List<Visit> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public void delete(Visit visit) {
        jpaRepository.delete(visit);
    }
}
