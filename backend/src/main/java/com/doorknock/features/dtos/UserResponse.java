package com.doorknock.features.dtos;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email
) {
}
