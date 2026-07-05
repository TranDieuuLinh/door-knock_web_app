package com.doorknock.features.service;

import com.doorknock.features.model.dtos.UserWithVisitStatsResponse;
import com.doorknock.features.model.dtos.user.UserPageRequest;
import org.springframework.data.domain.Page;

public interface VolunteerService {

    Page<UserWithVisitStatsResponse> getAllWithVisitStats(UserPageRequest request);
}
