package com.doorknock.features.user;

import com.doorknock.features.user.dto.CreateUserRequest;
import com.doorknock.features.user.dto.UpdateUserRequest;
import com.doorknock.features.user.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        UserEntity entity = new UserEntity();
        entity.setName(request.name());
        entity.setEmail(request.email());
        return toResponse(userRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
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
    public UserResponse update(Long id, UpdateUserRequest request) {
        UserEntity entity = findOrThrow(id);
        entity.setName(request.name());
        entity.setEmail(request.email());
        return toResponse(userRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(findOrThrow(id));
    }

    private UserEntity findOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }

    private UserResponse toResponse(UserEntity entity) {
        return new UserResponse(entity.getId(), entity.getName(), entity.getEmail());
    }
}
