package com.doorknock.features.mapper;

import com.doorknock.features.model.dtos.user.UserResponse;
import com.doorknock.features.model.entities.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getTerritory(),
                user.getCampaign(),
                user.getRole()
        );
    }
}
