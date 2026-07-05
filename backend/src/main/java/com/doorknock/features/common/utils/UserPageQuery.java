package com.doorknock.features.common.utils;

import com.doorknock.features.model.dtos.user.UserPageRequest;
import com.doorknock.features.model.entities.User;
import com.doorknock.features.repository.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class UserPageQuery {

    private UserPageQuery() {
    }

    public static Page<User> findUsers(
            UserRepository userRepository,
            UserPageRequest request,
            Pageable pageable
    ) {
        if (UserPageableUtils.isVisitStatsSort(request.sortBy())) {
            Sort.Direction direction = "desc".equalsIgnoreCase(request.sortOrder())
                    ? Sort.Direction.DESC
                    : Sort.Direction.ASC;
            return userRepository.findAllOrderByDoorKnocked(request.role(), direction, pageable);
        }

        if (request.role() == null) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findAllByRole(request.role(), pageable);
    }
}
