package com.doorknock.features.controller;

import com.doorknock.features.model.dtos.task.TaskUserResponse;
import com.doorknock.features.model.dtos.user.CreateUserRequest;
import com.doorknock.features.model.dtos.user.UserPageRequest;
import com.doorknock.features.model.dtos.user.UpdateUserRequest;
import com.doorknock.features.model.dtos.user.UserResponse;
import com.doorknock.features.model.dtos.UserWithVisitStatsResponse;
import com.doorknock.features.service.task.TaskService;
import com.doorknock.features.service.user.UserService;
import com.doorknock.features.service.VolunteerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("user")
    public ResponseEntity<List<TaskUserResponse>> getTaskByUserId(String userEmail){
        // Logic will be change to email as other developers finish setting up jwt
        var tasks = taskService.getTaskByUserEmail(userEmail);
        return ResponseEntity.ok(tasks);
    }
}
