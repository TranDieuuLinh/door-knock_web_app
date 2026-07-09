package com.doorknock.features.service.task;

import com.doorknock.features.model.dtos.task.TaskUserResponse;
import com.doorknock.features.repository.task.TaskRepository;
import com.doorknock.features.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskUserResponse> getTaskByUserEmail(String userEmail){
        var user = userRepository.findUserByEmail(userEmail);
        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found with email: " + userEmail
            );
        }
        return taskRepository.findTaskByUserID(user.get().getUserId()).stream()
                .map(task -> new TaskUserResponse(task.getTaskId(), task.getTaskStatus(), task.getHousehold()))
                .toList();
    }


}
