package io.stream.com.components;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheSchedule {

    private final static int TWO_HOURS = 200000;

    @Scheduled(fixedDelay=TWO_HOURS)
    public void UPDATE(){
        System.out.println("Hi! at " + new Date());
    } 

}