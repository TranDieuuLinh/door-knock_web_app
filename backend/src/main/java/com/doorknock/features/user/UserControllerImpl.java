package com.doorknock.features.user;

import com.doorknock.features.user.dto.CreateUserRequest;
import com.doorknock.features.user.dto.UpdateUserRequest;
import com.doorknock.features.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponse> create(CreateUserRequest request) {
        UserResponse created = userService.create(request);
        return ResponseEntity.created(URI.create("/api/users/" + created.id())).body(created);
    }

    @Override
    public UserResponse getById(Long id) {
        return userService.getById(id);
    }

    @Override
    public List<UserResponse> getAll() {
        return userService.getAll();
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest request) {
        return userService.update(id, request);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }
}
