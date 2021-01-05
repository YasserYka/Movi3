package io.stream.com.configurations;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSEConfiguration {
 
    @Bean 
    public ScheduledExecutorService scheduledExecutorService(){

        return Executors.newScheduledThreadPool(1);
    }
}
