package io.stream.com.configurations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadConfiguration {
 
    @Bean 
    @Qualifier("sse-video-processing")
    public ScheduledExecutorService scheduledExecutorService(){

        return Executors.newScheduledThreadPool(1);
    }

    @Bean
    @Qualifier("video-processing")
    public ExecutorService singleThreadExecutor(){

        return Executors.newSingleThreadExecutor();
    }
}
