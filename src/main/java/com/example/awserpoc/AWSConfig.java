package com.example.awserpoc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.entityresolution.EntityResolutionClient;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.StsException;


@Configuration
public class AWSConfig {

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.roleArn}")
    private String roleArn;

    @Value("${aws.roleSessionName}")
    private String roleSessionName;

    @Value("${aws.region}")
    private String region;

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));
    }

    @Bean
    public StsClient stsClient(AwsCredentialsProvider awsCredentialsProvider) {
        return StsClient.builder()
                .region(Region.EU_WEST_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    public EntityResolutionClient entityResolutionClient(StsClient stsClient) {
        AwsCredentialsProvider assumedCredentials = assumeRole(stsClient);
        return EntityResolutionClient.builder()
                .region(Region.EU_WEST_1)
                .credentialsProvider(assumedCredentials)
                .build();
    }

    private AwsCredentialsProvider assumeRole(StsClient stsClient) {
        AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
                .roleArn(roleArn)
                .roleSessionName(roleSessionName)
                .build();

        try {
            AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);
            return StaticCredentialsProvider.create(AwsSessionCredentials.create(
                    roleResponse.credentials().accessKeyId(),
                    roleResponse.credentials().secretAccessKey(),
                    roleResponse.credentials().sessionToken()));
        } catch (StsException e) {
            throw new RuntimeException("Failed to assume AWS role", e);
        }
    }
}

