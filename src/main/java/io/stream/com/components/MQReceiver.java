package io.stream.com.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import io.stream.com.configurations.RabbitMQConfiguration;
import io.stream.com.models.MQVideoProcessingMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MQReceiver {

    @RabbitListener(queues = RabbitMQConfiguration.queueName)
    public void receiver(MQVideoProcessingMessage message){ 
        log.info("Message received from queue with body {}", message.toString()); 
    }

}