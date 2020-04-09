package io.stream.com.utils;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;

public class BashUtil {

    @Value("${bash.path}")
    private static String bashPath;

    @Value("${bash.extract.filename}")
    private static String bashExtractFilename;

    public static ProcessBuilder processBuilder(ProcessType processType, String... args){
        String[] commands = new String[args.length + 2];
        int i = 0;
        
        commands[i++] = "/bin/bash";
        commands[i++] = pathOfBashScript(processType);

        for (String arg : args){ commands[i++] = arg; }

        return new ProcessBuilder(commands);
    }

    private static String pathOfBashScript(ProcessType processType){
        if(processType == ProcessType.extract_audio)
            return bashPath + bashExtractFilename;
        return null;
    }

    @SneakyThrows(IOException.class)
    public static InputStream runProcess(ProcessBuilder processBuilder){
        Process process = processBuilder.start();
        return process.getInputStream();
    }
}
