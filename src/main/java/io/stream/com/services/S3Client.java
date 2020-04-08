package io.stream.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class S3Client {

    @Autowired
    private AmazonS3 client;

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${s3.bucket.url}")
    private String url;

    public void upload(MultipartFile file) { }

}
