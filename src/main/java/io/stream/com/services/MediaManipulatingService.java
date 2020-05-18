package io.stream.com.services;

import io.stream.com.utils.BashUtil;
import io.stream.com.utils.ProcessType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MediaManipulatingService {

    @Value("${movie.directory}")
    private String movieDirectory;

    public void startExtractionProcess(String originalFilename){ 
        BashUtil.runProcess(BashUtil.processBuilder(ProcessType.extract_audio, movieDirectory+originalFilename)); 
    }

    public  void startConvertingProcess(String originalFilename){ 
        BashUtil.runProcess(BashUtil.processBuilder(ProcessType.convert, movieDirectory+originalFilename));
    }

}
