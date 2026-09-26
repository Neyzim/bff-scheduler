package com.ney.bff_scheduler.infrastructure.client;

import com.ney.bff_scheduler.business.dto.in.TaskDtoRequest;
import com.ney.bff_scheduler.business.dto.out.TaskDtoResponse;
import com.ney.bff_scheduler.business.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "bffTaskManager", url = "${taskManager.url}")
public interface TaskClient {


    @PostMapping
    TaskDtoResponse saveTask(@RequestBody TaskDtoRequest dto,
                             @RequestHeader("Authorization") String token);

    @GetMapping("/scheduled")
    List<TaskDtoResponse> getTaskListPerPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader("Authorization") String token);

    @GetMapping("/my-tasks")
   List<TaskDtoResponse> getTaskByEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deleteTaskById(String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDtoResponse updateTaskNotificationStatus(@RequestParam("Status") NotificationStatusEnum status,
                                                 @RequestParam String id,
                                                 @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDtoResponse updateTask(@RequestBody TaskDtoRequest dto,
                               @RequestParam String id,
                               @RequestHeader("Authorization") String token);
}
