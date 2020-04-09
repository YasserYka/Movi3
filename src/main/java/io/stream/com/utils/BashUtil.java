package io.stream.com.utils;

import org.springframework.beans.factory.annotation.Value;

public class BashUtil {

    @Value("${bash.path}")
    private static String bashPath;

    @Value("${bash.extract.filename}")
    private static String bashExtractFilename;

    //Takes
    public static String[] buildCommand(ProcessType processType, String... args){

        String[] command = new String[args.length + 2];
        int i = 0;
        command[i++] = "/bin/bash";
        command[i++] = pathOfBashScript(processType);

        for (String arg : args){ command[i++] = arg; }

        return command;
    }

    private static String pathOfBashScript(ProcessType processType){
        if(processType == ProcessType.extract_audio)
            return bashPath + bashExtractFilename;

        return null;
    }
}
