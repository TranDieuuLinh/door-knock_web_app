package com.doorknock.features.model.dtos.user;
import com.doorknock.features.common.enums.VolunteerRoles;


public record UserPageRequest(
    int page,
    int size,
    VolunteerRoles role,
    String sortBy,
    String sortOrder
){}