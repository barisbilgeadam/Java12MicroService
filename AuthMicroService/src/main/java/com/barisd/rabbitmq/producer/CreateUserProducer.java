package com.barisd.rabbitmq.producer;

import com.barisd.rabbitmq.model.SaveAuthModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CreateUserProducer {

    private final RabbitTemplate rabbitTemplate;

    public CreateUserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void convertAndSend(SaveAuthModel model){
        rabbitTemplate.convertAndSend("direct-exchange-auth","save-auth-binding-key",model);
    }
}
