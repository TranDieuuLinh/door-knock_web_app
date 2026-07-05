package com.doorknock.features.service.user;

import com.doorknock.features.model.dtos.user.CreateUserRequest;
import com.doorknock.features.model.dtos.user.UserPageRequest;
import com.doorknock.features.model.dtos.user.UpdateUserRequest;
import com.doorknock.features.model.dtos.user.UserResponse;
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
