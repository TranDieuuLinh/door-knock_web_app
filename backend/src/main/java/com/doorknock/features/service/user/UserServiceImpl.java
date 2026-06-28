package com.doorknock.features.service.user;

import com.doorknock.features.model.dtos.user.CreateUserRequest;
import com.doorknock.features.model.dtos.user.UserPageRequest;
import com.doorknock.features.model.dtos.user.UpdateUserRequest;
import com.doorknock.features.model.dtos.user.UserResponse;
import com.doorknock.features.model.dtos.user.UserWithVisitStatsResponse;
import com.doorknock.features.model.dtos.visit.VisitStats;
import com.doorknock.features.model.entities.User;
import com.doorknock.features.repository.user.UserRepository;
import com.doorknock.features.repository.visit.VisitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 20;
    private static final int MAX_SIZE = 100;
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "email", "createdAt");

    private final UserRepository userRepository;
    private final VisitRepository visitRepository;

    public UserServiceImpl(UserRepository userRepository, VisitRepository visitRepository) {
        this.userRepository = userRepository;
        this.visitRepository = visitRepository;
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
    public Page<UserResponse> getAll(UserPageRequest request) {
        Pageable pageable = buildPageable(request);
        return findUsers(request, pageable).map(this::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserWithVisitStatsResponse> getAllWithVisitStats(UserPageRequest request) {
        Pageable pageable = buildPageable(request);
        Page<User> users = findUsers(request, pageable);

        List<UUID> userIds = users.getContent().stream()
                .map(User::getUserId)
                .toList();
        Map<UUID, VisitStats> statsByUserId = visitRepository.getStatsByUserIds(userIds);

        return users.map(user -> toResponseWithVisitStats(user, statsByUserId.get(user.getUserId())));
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

    private Page<User> findUsers(UserPageRequest request, Pageable pageable) {
        if (request.role() == null) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findAllByRole(request.role(), pageable);
    }

    private User findOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getName(),
                user.getTerritory(),
                user.getCampaign(),
                user.getRole()
        );
    }

    private UserWithVisitStatsResponse toResponseWithVisitStats(User user, VisitStats stats) {
        VisitStats visitStats = stats != null ? stats : VisitStats.empty();
        return new UserWithVisitStatsResponse(toResponse(user), visitStats);
    }

    private Pageable buildPageable(UserPageRequest request) {
        int page = request.page() < 0 ? DEFAULT_PAGE : request.page();
        int size = request.size() <= 0 ? DEFAULT_SIZE : Math.min(request.size(), MAX_SIZE);

        String sortBy = request.sortBy() != null && ALLOWED_SORT_FIELDS.contains(request.sortBy())
                ? request.sortBy()
                : "name";
        Sort.Direction direction = "desc".equalsIgnoreCase(request.sortOrder())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(direction, sortBy));
    }
}
