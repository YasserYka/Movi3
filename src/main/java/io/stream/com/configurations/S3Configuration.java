package io.stream.com.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Configuration {

    @Value("${s3.access.name}")
    private String accessKey;

    @Value("${s3.access.secret}")
    private String accessSecret;

    @Bean
    public AmazonS3 amazonS3(){ return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, accessSecret))).withRegion(Regions.DEFAULT_REGION).build(); }
}
