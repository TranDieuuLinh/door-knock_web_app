package com.doorknock.features.model.dtos.User;

import com.doorknock.features.common.enums.VolunteerRoles;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String territory,
        VolunteerRoles role,
        String campaign
) {
}
