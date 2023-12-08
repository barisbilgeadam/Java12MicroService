package com.barisd.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    String directExchangeAuth="direct-exchange-auth";
    @Bean
    DirectExchange directExchangeAuth(){
        return new DirectExchange(directExchangeAuth);
    }

    String queueAuth="queue-auth";
    @Bean
    Queue queueAuth(){
        return new Queue(queueAuth);
    }


    String saveAuthBindingKey="save-auth-binding-key";
    public Binding bindingSaveAuthDirectExchange(Queue queueAuth,DirectExchange directExchangeAuth ){
        return BindingBuilder.bind(queueAuth).to(directExchangeAuth).with(saveAuthBindingKey);
    }


}
