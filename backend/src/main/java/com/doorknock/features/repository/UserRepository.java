package com.doorknock.features.user.repository;

import com.doorknock.features.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    List<User> findAll();

    void delete(User user);
}
