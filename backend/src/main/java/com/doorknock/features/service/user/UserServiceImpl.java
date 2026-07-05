package com.doorknock.features.service.user;

import com.doorknock.features.common.utils.UserPageQuery;
import com.doorknock.features.common.utils.UserPageableUtils;
import com.doorknock.features.mapper.UserMapper;
import com.doorknock.features.model.dtos.user.CreateUserRequest;
import com.doorknock.features.model.dtos.user.UserPageRequest;
import com.doorknock.features.model.dtos.user.UpdateUserRequest;
import com.doorknock.features.model.dtos.user.UserResponse;
import com.doorknock.features.model.entities.User;
import com.doorknock.features.repository.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(UUID id) {
        return UserMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponse> getAll(UserPageRequest request) {
        Pageable pageable = UserPageableUtils.from(request);
        return findUsers(request, pageable).map(UserMapper::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse update(UUID id, UpdateUserRequest request) {
        User user = findOrThrow(id);
        user.setName(request.name());
        user.setEmail(request.email());
        return UserMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(findOrThrow(id));
    }

    private Page<User> findUsers(UserPageRequest request, Pageable pageable) {
        return UserPageQuery.findUsers(userRepository, request, pageable);
    }

    private User findOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }
}
