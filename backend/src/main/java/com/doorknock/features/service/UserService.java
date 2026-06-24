package com.doorknock.features.service;

import com.doorknock.features.dtos.CreateUserRequest;
import com.doorknock.features.dtos.UpdateUserRequest;
import com.doorknock.features.dtos.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getById(UUID id);

    List<UserResponse> getAll();

    UserResponse update(UUID id, UpdateUserRequest request);

    void delete(UUID id);
}
