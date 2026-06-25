package com.doorknock.features.service;

import com.doorknock.features.model.dtos.User.CreateUserRequest;
import com.doorknock.features.model.dtos.User.UserPageRequest;
import com.doorknock.features.model.dtos.User.UpdateUserRequest;
import com.doorknock.features.model.dtos.User.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getById(UUID id);

    Page<UserResponse> getAll(UserPageRequest request);

    List<UserResponse> getAll();

    UserResponse update(UUID id, UpdateUserRequest request);

    void delete(UUID id);
}
