package com.ney.bff_scheduler.business;

import com.ney.bff_scheduler.business.dto.out.TaskDtoResponse;
import com.ney.bff_scheduler.infrastructure.client.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

   private final NotificationClient notificationClient;

    public void sendEmail(TaskDtoResponse taskDto){
        notificationClient.sendEmail(taskDto);
    }
}
