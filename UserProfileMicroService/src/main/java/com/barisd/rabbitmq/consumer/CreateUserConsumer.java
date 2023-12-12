package com.barisd.rabbitmq.consumer;

import com.barisd.rabbitmq.model.SaveAuthModel;
import com.barisd.repository.entity.UserProfile;
import com.barisd.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth")
    public void createUserFromQueue(SaveAuthModel model){
        userProfileService.save(UserProfile.builder()
                        .authid(model.getAuthid())
                        .username(model.getUsername())
                        .email(model.getEmail())
                .build());
    }
}
