package io.stream.com.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.stream.com.components.Receiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

@Configuration
public class RabbitMQConfiguration{

    @Value("${mq.queue.name}")
    private String queueName;
    
    @Value("${mq.queue.exchange}")
    private String exchangeName;

    @Value("${mq.queue.routing.key}")
    private String routingKey;

    @Bean 
    public Queue queue(){ return new Queue(queueName, false); }

    @Bean
    public Binding biniding(Queue queue, TopicExchange exchange){ return BindingBuilder.bind(queue).to(exchange).with(routingKey); }

    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(messageListenerAdapter);
        container.setPrefetchCount(1);
        return container;
    }

    @Bean
    public TopicExchange topicExchange(){ return new TopicExchange(exchangeName); }

    @Bean
    public MessageListenerAdapter listenerAdapter(Receiver receiver) {
      return new MessageListenerAdapter(receiver, "receiver");
    }

}