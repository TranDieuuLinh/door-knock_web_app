package com.doorknock.features.mapper;

import com.doorknock.features.model.dtos.TaskHouseholdResponse;
import com.doorknock.features.model.entities.Household;
import com.doorknock.features.model.entities.Task;

public final class TaskHouseholdMapper {

    private TaskHouseholdMapper() {
    }

    public static TaskHouseholdResponse toResponse(Task task, Household household) {
        return new TaskHouseholdResponse(
                task.getTaskId(),
                task.getTaskStatus(),
                household.getHouseholdId(),
                household.getAddress(),
                household.getSuburb(),
                household.getPostcode(),
                household.getFamilyName()
        );
    }
}
