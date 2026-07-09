package com.doorknock.features.model.dtos.task;
import com.doorknock.features.common.enums.TaskStatus;
import com.doorknock.features.model.entities.Household;

import java.util.UUID;

public record TaskUserResponse (
        UUID taskId,
        TaskStatus taskStatus,
        Household household
){ }
