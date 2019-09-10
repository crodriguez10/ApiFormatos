/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demojasper;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cesar.rodriguez
 */
@Configuration
public class S3Config {

    @Value("${amazon.aws.accesskey}")
    private String awsId;

    @Value("${amazon.aws.secretkey}")
    private String awsKey;

    @Bean
    public AmazonS3 s3client() {
        InstanceProfileCredentialsProvider provider = new InstanceProfileCredentialsProvider(true);
        return  AmazonS3ClientBuilder.standard()
                .withCredentials(provider)
                .build();
    }
}
