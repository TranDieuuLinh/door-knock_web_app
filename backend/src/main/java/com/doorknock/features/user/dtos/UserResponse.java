package com.doorknock.features.user.dtos;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email
) {
}
