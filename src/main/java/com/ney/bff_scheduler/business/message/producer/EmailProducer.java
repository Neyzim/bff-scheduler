package com.ney.bff_scheduler.business.message.producer;

import com.ney.bff_scheduler.business.dto.out.TaskDtoResponse;
import com.ney.bff_scheduler.business.message.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;


    public void sendEmail(TaskDtoResponse dto){
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_EMAIL,
                RabbitMqConfig.ROUTING_KEY,
                dto);
    }
}
