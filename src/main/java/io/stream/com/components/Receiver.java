package io.stream.com.components;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Receiver {

    public void receiver(String message){ log.info("Message received from queue with body {}", message); }

}