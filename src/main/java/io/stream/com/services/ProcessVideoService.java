package io.stream.com.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProcessVideoService {

    private ProcessBuilder buildProcess(){

        return new ProcessBuilder("/bin/bash", "-c", "yes n | ffmpeg -i sample.mp4 output.avi 2>&1");
    }

    @SneakyThrows(IOException.class)
    private void process(){

        buildProcess().start();
        log.info("Processing started for ..");
    }
}