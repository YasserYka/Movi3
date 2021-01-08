package io.stream.com.components;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import io.stream.com.models.dtos.MQMessage;
import io.stream.com.services.ProcessService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MQReceiver {

    @Autowired
    private ProcessService processService;

    @RabbitListener(queues = "processqueue")
    public void receiver(MQMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {

        log.info("Message received with file name {}", message.getFilename());

        boolean processing_succeed = processService.process(message);

        if (processing_succeed)
            channel.basicAck(tag, false);
        else
            channel.basicReject(tag, true);

        
    }

}