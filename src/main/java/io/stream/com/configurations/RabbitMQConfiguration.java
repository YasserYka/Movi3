package io.stream.com.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.stream.com.components.MQReceiver;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitMQConfiguration{

    public static final String queueName = "processqueue";
    
    public static final String exchangeName = "processexchange";


    @Bean 
    public Queue queue(){ return new Queue(queueName, false); }

    @Bean
    public Binding biniding(Queue queue, TopicExchange exchange){ 

      return BindingBuilder.bind(queue).to(exchange).with(queueName); 
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange topicExchange(){ 

      return new TopicExchange(exchangeName); 
    }

}