package io.stream.com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProcessVideoService {

    Pattern estimatedDurationPattern = Pattern.compile("Duration: (.*?),");
    Pattern timeDurationPattern = Pattern.compile("time=(.*?)\s");

    private ProcessBuilder buildProcess(){

        return new ProcessBuilder("/bin/bash", "-c", "yes n | ffmpeg -i sample.mp4 output.avi 2>&1");
    }

    private BufferedReader getBufferedReader(Process process){

        return new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    @SneakyThrows(IOException.class)
    private void process(){
        
        BufferedReader bufferedReader = getBufferedReader(buildProcess().start());
        String bufferedLine;
        double estimatedDuration = 0, currentDuration = 0;

        while ((bufferedLine = bufferedReader.readLine()) != null) {
            
            Matcher estimatedDurationMatcher = estimatedDurationPattern.matcher(bufferedLine);
            if (estimatedDurationMatcher.find())
                estimatedDuration = parseDuration(estimatedDurationMatcher.group(2));

            
            Matcher timeDurationMatcher = timeDurationPattern.matcher(bufferedLine);
            if (timeDurationMatcher.find())
                currentDuration = parseDuration(timeDurationMatcher.group(2));

            int progress = (int) ((currentDuration / estimatedDuration) * 100);
        }
        
    }

    // convert dd:dd:dd into seconds
    private double parseDuration(String duration){

        String[] durations = duration.split(":");

        return Integer.parseInt(durations[0]) * 3600 + Integer.parseInt(durations[1]) * 60 + Double.parseDouble(durations[2]);
    }
 
}