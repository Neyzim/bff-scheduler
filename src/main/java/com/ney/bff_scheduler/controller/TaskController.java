package com.ney.bff_scheduler.controller;


import com.ney.bff_scheduler.business.TaskService;
import com.ney.bff_scheduler.business.dto.in.TaskDtoRequest;
import com.ney.bff_scheduler.business.dto.out.TaskDtoResponse;
import com.ney.bff_scheduler.business.enums.NotificationStatusEnum;
import com.ney.bff_scheduler.infrastructure.client.configs.SecurityConfigs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name= "Tasks", description = "Task related operations")
@SecurityRequirement(name = SecurityConfigs.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Save Task", description = "Communicates with Task Manager Api to create a New Task")
    @ApiResponse(responseCode = "200", description = "Task Saved")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<TaskDtoResponse> saveTask(@RequestBody TaskDtoRequest dto,
                                                    @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.saveTask(dto, token));
    }

    @GetMapping("/scheduled")
    @Operation(summary = "Search Task by Scheduled Date", description = "Communicates with Task Manager Api to Search Task by Scheduled Date")
    @ApiResponse(responseCode = "200", description = "Task found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<List<TaskDtoResponse>> getTaskListPerPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader(value = "Authorization", required = false) String token

    ) {
        return ResponseEntity.ok(taskService.getTaskPerPeriod(startDate, finalDate, token));
    }

    @GetMapping("/my-tasks")
    @Operation(summary = "Search Tasks by User Email", description = "Communicates with Task Manager Api to Search Search Tasks by User Email")
    @ApiResponse(responseCode = "200", description = "Tasks found")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<List<TaskDtoResponse>> getTaskByEmail(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.getTasksByUserEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Delete Task", description = "Communicates with Task Manager Api to Delete a task using its Id")
    @ApiResponse(responseCode = "200", description = "Tasks deleted")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<Void> deleteTaskById(String id,
                                               @RequestHeader(value = "Authorization", required = false) String token) {
            taskService.deleteTaskById(id, token);
            return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Update Task Status", description = "Communicates with Task Manager Api to Update Task Status")
    @ApiResponse(responseCode = "200", description = "Tasks Status Updated")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<TaskDtoResponse> updateTaskNotificationStatus(@RequestParam("Status") NotificationStatusEnum status,
                                                                        @RequestParam String id,
                                                                        @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.changeTaskStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Update Task", description = "Communicates with Task Manager Api to Update Task")
    @ApiResponse(responseCode = "200", description = "Tasks Status")
    @ApiResponse(responseCode = "500", description = "Service Error")
    public ResponseEntity<TaskDtoResponse> updateTask(@RequestBody TaskDtoRequest dto,
                                                      @RequestParam String id,
                                                      @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(taskService.updateTask(dto, id, token));
    }
}