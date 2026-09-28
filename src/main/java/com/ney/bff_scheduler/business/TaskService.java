package com.ney.bff_scheduler.business;

import com.ney.bff_scheduler.business.dto.in.TaskDtoRequest;
import com.ney.bff_scheduler.business.dto.out.TaskDtoResponse;
import com.ney.bff_scheduler.business.enums.NotificationStatusEnum;
import com.ney.bff_scheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient taskClient;

    public TaskDtoResponse saveTask(TaskDtoRequest taskDto, String token) {
        return taskClient.saveTask(taskDto, token);
    }

    public List<TaskDtoResponse> getTaskPerPeriod(LocalDateTime startDate, LocalDateTime finalDate, String token) {
        return taskClient.getTaskListPerPeriod(startDate, finalDate, token);
    }

    public List<TaskDtoResponse> getTasksByUserEmail(String token) {
        return taskClient.getTaskByEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        taskClient.deleteTaskById(id, token);
    }

    public TaskDtoResponse changeTaskStatus(NotificationStatusEnum statusEnum, String id, String token) {
       return taskClient.updateTaskNotificationStatus(statusEnum, id, token);
    }

    public TaskDtoResponse updateTask(TaskDtoRequest dto, String id, String token) {
        return taskClient.updateTask(dto, id, token);
    }
}


