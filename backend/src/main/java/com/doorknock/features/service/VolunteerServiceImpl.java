package com.doorknock.features.service;

import com.doorknock.features.common.utils.UserPageQuery;
import com.doorknock.features.common.utils.UserPageableUtils;
import com.doorknock.features.mapper.UserVisitMapper;
import com.doorknock.features.model.dtos.UserWithVisitStatsResponse;
import com.doorknock.features.model.dtos.user.UserPageRequest;
import com.doorknock.features.model.entities.User;
import com.doorknock.features.repository.user.UserRepository;
import com.doorknock.features.repository.visit.VisitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class VolunteerServiceImpl implements VolunteerService {

    private final UserRepository userRepository;
    private final VisitRepository visitRepository;

    public VolunteerServiceImpl(UserRepository userRepository, VisitRepository visitRepository) {
        this.userRepository = userRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public Page<UserWithVisitStatsResponse> getAllWithVisitStats(UserPageRequest request) {
        Pageable pageable = UserPageableUtils.from(request);
        Page<User> users = UserPageQuery.findUsers(userRepository, request, pageable);

        List<UUID> userIds = users.getContent().stream()
                .map(User::getUserId)
                .toList();

        var statsByUserId = visitRepository.getStatsByUserIds(userIds);

        return users.map(user -> UserVisitMapper.toResponseWithVisitStats(
                user,
                statsByUserId.get(user.getUserId())
        ));
    }
}
