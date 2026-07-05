package com.doorknock.features.repository.user;

import com.doorknock.features.common.enums.VolunteerRoles;
import com.doorknock.features.model.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {

    Page<User> findByRole(VolunteerRoles role, Pageable pageable);
    Optional<User> findByEmail(String userEmail);
}
