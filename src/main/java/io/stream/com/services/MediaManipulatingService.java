package io.stream.com.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
    This class must be deleted after AWS lambda setup
*/

@Deprecated
@Service
public class MediaManipulatingService {

	@Value("${upload.path}")
	private String uploadPath;

    public void startExtractionProcess(String originalFilename){ 
        //BashUtil.runProcess(BashUtil.processBuilder(ProcessType.extract_audio, movieDirectory+originalFilename)); 
    }

    public  void startConvertingProcess(String originalFilename){ 
        //BashUtil.runProcess(BashUtil.processBuilder(ProcessType.convert, movieDirectory+originalFilename));
    }

}
