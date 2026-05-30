package com.doorknock.features.user.dto;

/** Outgoing payload for user read and write responses. */
public record UserResponse(
        Long id,
        String name,
        String email
) {
}
