package com.doorknock.features.repository.user;

import com.doorknock.features.common.enums.VolunteerRoles;
import com.doorknock.features.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByRole(VolunteerRoles role, Pageable pageable);

    Page<User> findAllOrderByDoorKnocked(
            VolunteerRoles role,
            Sort.Direction direction,
            Pageable pageable
    );

    Optional<User> findUserByEmail(String userEmail);

    void delete(User user);
}
