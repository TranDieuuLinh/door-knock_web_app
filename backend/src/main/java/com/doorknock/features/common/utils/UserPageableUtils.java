package com.doorknock.features.common.utils;

import com.doorknock.features.model.dtos.user.UserPageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public final class UserPageableUtils {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "email", "createdAt");
    private static final String DEFAULT_SORT_FIELD = "name";

    private UserPageableUtils() {
    }

    public static Pageable from(UserPageRequest request) {
        return PageableUtils.build(
                request.page(),
                request.size(),
                request.sortBy(),
                request.sortOrder(),
                ALLOWED_SORT_FIELDS,
                DEFAULT_SORT_FIELD
        );
    }
}
