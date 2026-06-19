package com.doorknock.features.user.repository;

import com.doorknock.features.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface UserJpaRepository extends JpaRepository<User, UUID> {
}
