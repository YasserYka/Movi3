package io.stream.com.controllers;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.SneakyThrows;

@RestController
public class SSEController {
    
    @Autowired
    private SseEmitter emitter;
    @Autowired
    private ScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    public void initialize() {
        
        scheduledExecutorService.scheduleAtFixedRate(() -> {

            emitterSend();
        }, 0, 1, TimeUnit.SECONDS);
    }

    @SneakyThrows(IOException.class)
    public void emitterSend(){

        emitter.send("Hi!");
    }

    @GetMapping("/sse-video-processing")
    public SseEmitter fetchData() {

        return emitter;
    }
    
}
