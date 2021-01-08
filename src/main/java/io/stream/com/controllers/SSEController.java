package io.stream.com.controllers;

import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import io.stream.com.services.ProcessService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SSEController {

    @Autowired
    private ScheduledExecutorService scheduledExecutorService;

    @SneakyThrows(IOException.class)
    public void emitterSend(SseEmitter emitter){

        emitter.send(ProcessService.PROCESS_STATUS);
    }

    @GetMapping("/sse-process")
    public SseEmitter fetchData() {
        SseEmitter emitter = new SseEmitter(3600000L);

        emitter.onTimeout(() -> {

            log.info("SSE emitter timed out");
            emitter.complete();
        });

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            
            emitterSend(emitter);
        }, 0, 1, TimeUnit.SECONDS);

        return emitter;
    }
    
}
