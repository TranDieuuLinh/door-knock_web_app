package com.doorknock.features.service.task;

import com.doorknock.features.model.dtos.task.TaskUserResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskUserResponse> getTaskByUserEmail(String email);
}
