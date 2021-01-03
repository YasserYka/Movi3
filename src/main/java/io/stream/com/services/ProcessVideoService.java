package io.stream.com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProcessVideoService {

    Pattern estimatedDurationPattern = Pattern.compile("(Duration): (.*?),");
    Pattern timeDurationPattern = Pattern.compile("(time)=(.*?)\s");

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

        while ((bufferedLine = bufferedReader.readLine()) != null) {
            
        }
        
    }
 
}