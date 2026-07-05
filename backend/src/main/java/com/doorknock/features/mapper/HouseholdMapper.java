package com.doorknock.features.mapper;

import com.doorknock.features.model.dtos.household.HouseholdDetailResponse;
import com.doorknock.features.model.entities.Household;

public final class HouseholdMapper {

    private HouseholdMapper() {
    }

    public static HouseholdDetailResponse toDetail(Household household) {
        return new HouseholdDetailResponse(
                household.getHouseholdId(),
                household.getAddress(),
                household.getSuburb(),
                household.getNote(),
                household.getPostcode(),
                household.getPhone(),
                household.getBestTime(),
                household.getFamilyName()
        );
    }
}
