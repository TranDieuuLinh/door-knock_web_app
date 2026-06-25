package com.doorknock.features.model.dtos.User;

public record UserPageRequest(
    int page,
    int size,
    String sortBy,
    String sortOrder
){}