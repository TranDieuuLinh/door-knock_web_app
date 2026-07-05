package com.doorknock.features.model.dtos.task;
import com.doorknock.features.common.enums.TaskStatus;

import java.util.UUID;

public record TaskUserResponse (
        UUID taskId,
        TaskStatus taskStatus
){ }
