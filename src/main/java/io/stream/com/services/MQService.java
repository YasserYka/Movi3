package io.stream.com.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.stream.com.models.MQVideoProcessingMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MQService {

    @Autowired
    private RabbitTemplate template;

    @Value("${mq.queue.exchange}")
    private String exchangeName;

    @Value("${mq.queue.routing.key}")
    private String routingKey;

    public void send(MQVideoProcessingMessage message){ 
        template.convertAndSend(exchangeName, routingKey, message); 
        log.info("Message sent to queue with routing {} and body {}", routingKey, message);
    }
}