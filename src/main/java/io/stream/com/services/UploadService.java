package io.stream.com.services;

import io.stream.com.mappers.MovieMapper;
import io.stream.com.models.Movie;
import io.stream.com.models.dtos.MovieDto;
import io.stream.com.utils.MediaUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    @Autowired
    private MQService mqService;

    @Value("${upload.path}")
	private String uploadPath;

    public void upload(MultipartFile multipartFile, MovieDto movieDto){
        // Nope think it through
    }

    private void notifyMQ(String originalFilename, boolean isStoredInS3){

        //mqService.send(new MQMessage(VideoProcessType.extract_audio, originalFilename, isStoredInS3).toString());
        
    }

	@SneakyThrows(IOException.class)
	private void upload(MultipartFile multipartFile) {
		 Files.copy(multipartFile.getInputStream(), Paths.get(uploadPath + multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING); 
    }
    
    /*private boolean isNotSupported(String filename) {
        //if(!MediaUtil.isFormatSupported(filename))
            return false;
        log.warn("File With Not Supported Format Was Uploaded {}", filename);
        return true;
    }*/


}
