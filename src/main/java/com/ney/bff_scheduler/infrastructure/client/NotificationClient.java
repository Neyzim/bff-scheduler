package com.ney.bff_scheduler.infrastructure.client;

import com.ney.bff_scheduler.business.dto.in.TaskDtoRequest;
import com.ney.bff_scheduler.business.dto.out.TaskDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bffNotification", url = "${notification.url}")
public interface NotificationClient {


    @PostMapping
    void sendEmail(@RequestBody TaskDtoResponse taskDto);
}
