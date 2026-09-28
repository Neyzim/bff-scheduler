package com.ney.bff_scheduler.business.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ney.bff_scheduler.business.enums.NotificationStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDtoResponse {


    private String id;
    private String name;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime scheduledDate;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime changedAt;
    private NotificationStatusEnum status;
}
