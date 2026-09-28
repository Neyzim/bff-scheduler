package com.ney.bff_scheduler.business;

import com.ney.bff_scheduler.business.dto.in.LoginDtoRequest;
import com.ney.bff_scheduler.business.dto.out.TaskDtoResponse;
import com.ney.bff_scheduler.business.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TaskService taskService;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;
    @Value("${user.password}")
    private String password;


    @Scheduled(cron = "${cron.time}")
    public void getNextHourScheduledTasks(){
        String token = login(toRequestDto());
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime timePlusOneHour = LocalDateTime.now().plusHours(1);
        List<TaskDtoResponse> taskList = taskService.getTaskPerPeriod(time, timePlusOneHour, token);
        log.info("Tarefas Encontradas: " + taskList.toString());
        taskList.forEach(
                task -> {
                    emailService.sendEmail(task);
                    log.info("Email Enviado!" + task.getCreatedBy());
                    taskService.changeTaskStatus(NotificationStatusEnum.NOTIFIED, task.getId(), token);
                }
        );
    }

    public String login(LoginDtoRequest login){
        return userService.login(login);
    }

    public LoginDtoRequest toRequestDto(){
        return LoginDtoRequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}
