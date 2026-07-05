package com.doorknock.features.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

public final class PageableUtils {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final int MAX_SIZE = 100;

    private PageableUtils() {
    }

    public static Pageable build(
            int page,
            int size,
            String sortBy,
            String sortOrder,
            Set<String> allowedSortFields,
            String defaultSortField
    ) {
        int resolvedPage = page < 0 ? DEFAULT_PAGE : page;
        int resolvedSize = size <= 0 ? DEFAULT_SIZE : Math.min(size, MAX_SIZE);

        String resolvedSortBy = sortBy != null && allowedSortFields.contains(sortBy)
                ? sortBy
                : defaultSortField;
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(resolvedPage, resolvedSize, Sort.by(direction, resolvedSortBy));
    }

    public static Pageable buildPagingOnly(int page, int size) {
        int resolvedPage = page < 0 ? DEFAULT_PAGE : page;
        int resolvedSize = size <= 0 ? DEFAULT_SIZE : Math.min(size, MAX_SIZE);
        return PageRequest.of(resolvedPage, resolvedSize);
    }
}
