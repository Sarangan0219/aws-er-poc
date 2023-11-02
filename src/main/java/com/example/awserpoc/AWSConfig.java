package com.example.awserpoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.entityresolution.EntityResolutionClient;
import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.services.sts.model.AssumeRoleResponse;
import software.amazon.awssdk.services.sts.model.Credentials;
import software.amazon.awssdk.services.sts.model.StsException;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Configuration
public class AWSConfig {

    public StsClient stsClient() {
        AwsCredentials credentials = AwsBasicCredentials.create("AKIAQCGMVG643WLOHSRN",
                "RnVJRxj7jxPXNKLM6VQ8Elu3nME8+NMhWKcdSacq");
        StaticCredentialsProvider staticProvider = StaticCredentialsProvider.create(credentials);
        return StsClient.builder().region(Region.EU_WEST_1)
                .credentialsProvider(staticProvider).build();
    }


    public static EntityResolutionClient assumeGivenRole(StsClient stsClient, String roleArn, String roleSessionName) {

        try {
            AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
                    .roleArn(roleArn)
                    .roleSessionName(roleSessionName)
                    .build();

            AssumeRoleResponse roleResponse = stsClient.assumeRole(roleRequest);


            AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(AwsSessionCredentials.create(
                    roleResponse.credentials().accessKeyId(),
                    roleResponse.credentials().secretAccessKey(),
                    roleResponse.credentials().sessionToken()));


            Region region = Region.EU_WEST_1;
            return  EntityResolutionClient.builder()
                    .region(region)
                    .credentialsProvider(awsCredentialsProvider)
                    .build();

        } catch (StsException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }



    @Bean
    public EntityResolutionClient entityResolutionClient() {
        return assumeGivenRole(stsClient(),
                "arn:aws:iam::004724176825:role/scv-er-poc-er-service-sbox", "test");
    }

//    @Bean
//    public EntityResolutionClient entityResolutionClient() {
//        AwsCredentials credentials = AwsBasicCredentials.create("AKIAQCGMVG643WLOHSRN",
//                "RnVJRxj7jxPXNKLM6VQ8Elu3nME8+NMhWKcdSacq");
//        StaticCredentialsProvider staticProvider = StaticCredentialsProvider.create(credentials);
//
//        Region region = Region.EU_WEST_1;
//        EntityResolutionClient entityResolutionClient = EntityResolutionClient.builder()
//                .region(region)
//                .credentialsProvider(staticProvider)
//                .build();
//        return entityResolutionClient;
//    }


}
