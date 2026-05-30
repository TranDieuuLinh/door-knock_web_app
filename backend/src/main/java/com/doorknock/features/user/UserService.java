package com.doorknock.features.user;

import com.doorknock.features.user.dto.CreateUserRequest;
import com.doorknock.features.user.dto.UpdateUserRequest;
import com.doorknock.features.user.dto.UserResponse;

import java.util.List;

/**
 * Business logic contract for the user feature. Controllers depend on this
 * interface so implementations can be swapped or mocked in tests.
 */
public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getById(Long id);

    List<UserResponse> getAll();

    UserResponse update(Long id, UpdateUserRequest request);

    void delete(Long id);
}
