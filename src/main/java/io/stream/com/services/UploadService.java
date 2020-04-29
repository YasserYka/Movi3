package io.stream.com.services;

import io.stream.com.models.MQMessage;
import io.stream.com.models.Movie;
import io.stream.com.utils.MQMessageUtil;
import io.stream.com.utils.MediaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class UploadService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private S3Client s3Client;

    @Value("#{new Boolean('${s3.enabled}')}")
    private Boolean s3Enabled;

    /*@Autowired
    private MediaManipulatingService mediaManipulatingService;*/

    @Autowired
    private MQService mqService;

    public void upload(MultipartFile multipartFile){

        if(isNotSupported(multipartFile.getOriginalFilename()))
            return;

        Movie movie = Movie.builder().originalFilename(multipartFile.getOriginalFilename()).storedInS3(s3Enabled).build();

        movieService.save(movie);

        if(s3Enabled)
            s3Client.upload(multipartFile);
        else
            movieService.upload(multipartFile);

        //startProcess(movie.getOriginalFilename());
    }

    private void sendToMQ(String originalFilename){
        //TODO: enums would be better
        mqService.send(new MQMessage("extract", originalFilename, false).toString());
    }
    /*
    private void startProcess(String originalFilename){
        mediaManipulatingService.startExtractionProcess(originalFilename);
        mediaManipulatingService.startConvertingProcess(originalFilename);
    }
    */
    private boolean isNotSupported(String filename) {
        if(!MediaUtil.isFormatSupported(filename))
            return false;
        log.warn("File With Not Supported Format Was Uploaded {}", filename);
        return true;
    }


}
