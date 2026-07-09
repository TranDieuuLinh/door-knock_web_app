package com.doorknock.features.controller;

import com.doorknock.features.model.dtos.task.TaskHouseholdResponse;
import com.doorknock.features.service.household.HouseholdService;
import com.doorknock.features.service.task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final HouseholdService householdService;

    public TaskController(TaskService taskService, HouseholdService householdService) {
        this.taskService = taskService;
        this.householdService = householdService;
    }

    // Return the following task service status along with the household detail
    @GetMapping("/by-user")
    public ResponseEntity<List<TaskHouseholdResponse>> getTaskAndHouseholdDetailByUserEmail(
            @RequestParam String userEmail
    ) {
        var tasks = taskService.getTaskByUserEmail(userEmail);

        if (tasks == null || tasks.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        List<TaskHouseholdResponse> response = tasks.stream()
                .map(task -> {
                    var household = task.household();
                    var houseHoldInfo = householdService.getHouseholdDetail(household.getHouseholdId());

                    return new TaskHouseholdResponse(
                            task.taskId(),
                            task.taskStatus(),
                            houseHoldInfo.householdId(),
                            houseHoldInfo.address(),
                            houseHoldInfo.suburb(),
                            houseHoldInfo.postcode(),
                            houseHoldInfo.familyName()
                    );
                })
                .toList();

        return ResponseEntity.ok(response);
    }
}
