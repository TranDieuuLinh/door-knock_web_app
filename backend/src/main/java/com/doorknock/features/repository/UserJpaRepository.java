package com.doorknock.features.repository;

import com.doorknock.features.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface UserJpaRepository extends JpaRepository<User, UUID> {
}
