package io.stream.com.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class S3Client {

    @Autowired
    private AmazonS3 client;

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${s3.bucket.url}")
    private String url;

    public void upload(MultipartFile multipartFile) {
        File file = convertMultiPartToFile(multipartFile);
    }

    private File convertMultiPartToFile(MultipartFile multipartFile){
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()), "MultipartFile provided to S3Client is null");
        createOutputStream(file, multipartFile);
        return file;
    }

    @SneakyThrows(FileNotFoundException.class)
    private void createOutputStream(File file, MultipartFile multipartFile){
        writeOnStream(new FileOutputStream(file), multipartFile);
    }

    @SneakyThrows(IOException.class)
    private void writeOnStream(FileOutputStream stream, MultipartFile multipartFile){
        stream.write(multipartFile.getBytes());
        stream.close();
    }
}
