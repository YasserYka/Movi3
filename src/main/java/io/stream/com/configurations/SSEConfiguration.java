package io.stream.com.configurations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Configuration
public class SSEConfiguration {
    
    @Bean
    public SseEmitter sseEmitter(){

        return new SseEmitter();
    }

    @Bean 
    public ExecutorService executorService(){

        return Executors.newSingleThreadExecutor();
    }
}
