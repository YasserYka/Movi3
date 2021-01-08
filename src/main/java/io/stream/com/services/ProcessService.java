package io.stream.com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import io.stream.com.models.dtos.MQMessage;
import io.stream.com.models.dtos.ProcessStatus;
import io.stream.com.models.enums.MQEvent;
import io.stream.com.utils.BashUtil;
import lombok.SneakyThrows;

@Service
public class ProcessService {

    private static final Pattern ESTIMATED_DURATION_PATTERN = Pattern.compile("Duration: (.*?),");
    private static final Pattern TIME_DURATION_PATTERN = Pattern.compile("time=(.*?) ");

    private static final ExecutorService SINGLE_THREAD_EXECUTOR = Executors.newSingleThreadExecutor();
    private static ProcessStatus PROCESS_STATUS = new ProcessStatus(0, "Null", 0);

    public void process(MQMessage message){

        SINGLE_THREAD_EXECUTOR.execute(() -> {
            
            processVideo(message.getFilename(), message.getFilepath(), message.getEvent());
        });
                
    }

    @SneakyThrows(IOException.class)
    private void processVideo(String filename, String path, MQEvent event){

        BufferedReader bufferedReader = BashUtil.getBufferedReader(BashUtil.buildProcessFor(event).start());
    
        String bufferedLine;
        double estimatedDuration = 0, currentDuration = 0;

        PROCESS_STATUS.setFilename(filename);

        while ((bufferedLine = bufferedReader.readLine()) != null) {
            
            Matcher estimatedDurationMatcher = ESTIMATED_DURATION_PATTERN.matcher(bufferedLine);
            if (estimatedDurationMatcher.find()){
                estimatedDuration = parseDuration(estimatedDurationMatcher.group(1));
                PROCESS_STATUS.setEstimatedTime((int) estimatedDuration);
            }
            
            Matcher timeDurationMatcher = TIME_DURATION_PATTERN.matcher(bufferedLine);
            if (timeDurationMatcher.find())
                currentDuration = parseDuration(timeDurationMatcher.group(1));

            PROCESS_STATUS.setPercentage((int) ((currentDuration / estimatedDuration) * 100));
        }

    }

    // convert dd:dd:dd string into seconds
    private double parseDuration(String duration){

        String[] durations = duration.split(":");

        return Integer.parseInt(durations[0]) * 3600 + Integer.parseInt(durations[1]) * 60 + Double.parseDouble(durations[2]);
    }
 
}