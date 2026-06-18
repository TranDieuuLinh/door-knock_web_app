package com.doorknock.features.user.service;

import com.doorknock.features.user.dtos.CreateUserRequest;
import com.doorknock.features.user.dtos.UpdateUserRequest;
import com.doorknock.features.user.dtos.UserResponse;
import com.doorknock.features.user.model.User;
import com.doorknock.features.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(UUID id) {
        return toResponse(findOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponse update(UUID id, UpdateUserRequest request) {
        User user = findOrThrow(id);
        user.setName(request.name());
        user.setEmail(request.email());
        return toResponse(userRepository.save(user));
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(findOrThrow(id));
    }

    private User findOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
