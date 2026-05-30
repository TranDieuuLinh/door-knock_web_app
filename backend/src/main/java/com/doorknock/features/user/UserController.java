package com.doorknock.features.user;

import com.doorknock.features.user.dto.CreateUserRequest;
import com.doorknock.features.user.dto.UpdateUserRequest;
import com.doorknock.features.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/api/users")
public interface UserController {

    @PostMapping
    ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request);

    @GetMapping("/{id}")
    UserResponse getById(@PathVariable Long id);

    @GetMapping
    List<UserResponse> getAll();

    @PutMapping("/{id}")
    UserResponse update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
