package io.stream.com.services;

import io.stream.com.mappers.MovieMapper;
import io.stream.com.models.MQMessage;
import io.stream.com.models.Movie;
import io.stream.com.models.dtos.MovieDto;
import io.stream.com.utils.MediaUtil;
import io.stream.com.utils.VideoProcessType;
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

    @Autowired
    private MQService mqService;

    public void upload(MultipartFile multipartFile, MovieDto movieDto){

        if(isNotSupported(multipartFile.getOriginalFilename()))
            return;

        Movie movie = MovieMapper.map(movieDto);

        movie.setOriginalFilename(multipartFile.getOriginalFilename());
        movie.setStoredInS3(s3Enabled);

        movieService.save(movie);

        if(s3Enabled)
            s3Client.upload(multipartFile);
        else
            movieService.upload(multipartFile);

        notifyMQ(movie.getOriginalFilename(), movie.isStoredInS3());
    }

    private void notifyMQ(String originalFilename, boolean isStoredInS3){
        //TODO: enums would be better
        mqService.send(new MQMessage(VideoProcessType.extract_audio, originalFilename, isStoredInS3).toString());
    }

    private boolean isNotSupported(String filename) {
        if(!MediaUtil.isFormatSupported(filename))
            return false;
        log.warn("File With Not Supported Format Was Uploaded {}", filename);
        return true;
    }


}
