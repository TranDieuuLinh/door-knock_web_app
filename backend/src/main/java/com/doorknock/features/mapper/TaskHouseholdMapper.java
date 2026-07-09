package com.doorknock.features.mapper;

import com.doorknock.features.model.dtos.task.TaskHouseholdResponse;
import com.doorknock.features.model.dtos.task.TaskUserResponse;
import com.doorknock.features.model.entities.Household;

public final class TaskHouseholdMapper {

    private TaskHouseholdMapper() {
    }

    public static TaskHouseholdResponse fromTaskUser(TaskUserResponse task) {
        Household household = task.household();
        return new TaskHouseholdResponse(
                task.taskId(),
                task.taskStatus(),
                household.getHouseholdId(),
                household.getAddress(),
                household.getSuburb(),
                household.getPostcode(),
                household.getFamilyName()
        );
    }
}
