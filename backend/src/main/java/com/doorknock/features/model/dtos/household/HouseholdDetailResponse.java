package com.doorknock.features.model.dtos.household;

import java.time.LocalTime;
import java.util.UUID;

public record HouseholdDetailResponse(
        UUID householdId,
        String address,
        String suburb,
        String note,
        String postcode,
        int phone,
        LocalTime bestTime,
        String familyName
) {
}
