package com.example.awserpoc;


import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.entityresolution.EntityResolutionClient;
import software.amazon.awssdk.services.entityresolution.model.*;

import org.springframework.stereotype.Service;

@Service
public class EntityResolutionService {

    private final EntityResolutionClient client;

    @Autowired
    public EntityResolutionService(EntityResolutionClient client) {
        this.client = client;
    }

    public GetMatchIdRequest convertToRequest(GetMatchIdRequestDTO dto) {
        return GetMatchIdRequest.builder()
                .record(dto.getRecord())
                .workflowName(dto.getWorkflowName())
                .build();
    }

    public GetMatchIdResponse getMatchId(GetMatchIdRequestDTO dto) {
        GetMatchIdRequest request = convertToRequest(dto);
        return client.getMatchId(request);
    }
}
