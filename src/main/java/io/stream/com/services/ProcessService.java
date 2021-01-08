package io.stream.com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.stream.com.components.MQReceiver;
import io.stream.com.models.dtos.MQMessage;
import io.stream.com.models.enums.MQEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProcessService {

    Pattern estimatedDurationPattern = Pattern.compile("Duration: (.*?),");
    Pattern timeDurationPattern = Pattern.compile("time=(.*?) ");

    private ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private ProcessBuilder buildProcess(){

        return new ProcessBuilder("/bin/bash", "-c", "yes y | ffmpeg -i sample_720.mp4 output.avi 2>&1");
    }

    private BufferedReader getBufferedReader(Process process){

        return new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    public void process(MQMessage message){

        singleThreadExecutor.execute(() -> {
            
            processVideo(message.getFilename(), message.getFilepath(), message.getEvent());
        });
                
    }

    private void processVideo(String filename, String path, MQEvent event){

        
    } 



    // convert dd:dd:dd into seconds
    private double parseDuration(String duration){

        String[] durations = duration.split(":");

        return Integer.parseInt(durations[0]) * 3600 + Integer.parseInt(durations[1]) * 60 + Double.parseDouble(durations[2]);
    }
 
}