package io.stream.com.services;

import io.stream.com.repositories.MovieRepository;
import io.stream.com.utils.MediaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Slf4j
@Service
public class UploadService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private S3Client s3Client;

    private HashMap supportedFormats;

    public void upload(MultipartFile multipartFile){

        if(isNotSupported(multipartFile.getOriginalFilename()))
            return;


    }

    private boolean isNotSupported(String filename) {
        if(MediaUtil.isFormatSupported(filename))
            return false;
        log.warn("File With Not Supported Format Was Uploaded {}", filename);
        return true;
    }
}
