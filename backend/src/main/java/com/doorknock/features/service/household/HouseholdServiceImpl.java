package com.doorknock.features.service.household;

import com.doorknock.features.mapper.HouseholdMapper;
import com.doorknock.features.model.dtos.household.HouseholdDetailResponse;
import com.doorknock.features.repository.household.HouseholdRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class HouseholdServiceImpl implements HouseholdService {

    private final HouseholdRepository householdRepository;

    public HouseholdServiceImpl(HouseholdRepository householdRepository) {
        this.householdRepository = householdRepository;
    }

    @Override
    public HouseholdDetailResponse getHouseHoldDetailResponse(UUID taskId) {
        return householdRepository.findHouseholdByTaskId(taskId)
                .map(HouseholdMapper::toDetail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Household not found for task id: " + taskId
                ));
    }

    @Override
    public HouseholdDetailResponse getHouseholdDetail(UUID householdId){
        return householdRepository.findHouseholdDetail(householdId)
                .map(HouseholdMapper::toDetail)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Household not found for task id: " + householdId
                ));
    }
}

