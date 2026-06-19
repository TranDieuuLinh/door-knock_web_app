package com.doorknock.features.user.service;

import com.doorknock.features.user.dtos.CreateUserRequest;
import com.doorknock.features.user.dtos.UpdateUserRequest;
import com.doorknock.features.user.dtos.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getById(UUID id);

    List<UserResponse> getAll();

    UserResponse update(UUID id, UpdateUserRequest request);

    void delete(UUID id);
}
