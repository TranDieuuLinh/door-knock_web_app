package com.doorknock.features.common.utils;

import com.doorknock.features.model.dtos.user.UserPageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public final class UserPageableUtils {

    private static final Set<String> USER_SORT_FIELDS = Set.of(
            "name", "email", "createdAt", "territory", "campaign"
    );
    private static final Set<String> VISIT_STATS_SORT_FIELDS = Set.of("totalDoorKnocked");
    private static final String DEFAULT_SORT_FIELD = "name";

    private UserPageableUtils() {
    }

    public static boolean isVisitStatsSort(String sortBy) {
        return sortBy != null && VISIT_STATS_SORT_FIELDS.contains(sortBy);
    }

    public static Pageable from(UserPageRequest request) {
        if (isVisitStatsSort(request.sortBy())) {
            return PageableUtils.buildPagingOnly(request.page(), request.size());
        }

        return PageableUtils.build(
                request.page(),
                request.size(),
                request.sortBy(),
                request.sortOrder(),
                USER_SORT_FIELDS,
                DEFAULT_SORT_FIELD
        );
    }
}
