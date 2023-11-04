package com.example.awserpoc;


import com.example.awserpoc.dto.GetMatchIdRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.entityresolution.EntityResolutionClient;
import software.amazon.awssdk.services.entityresolution.model.*;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityResolutionService {

    private final EntityResolutionClient entityResolutionClient;

    // Constructor-based Dependency Injection
    @Autowired
    public EntityResolutionService(EntityResolutionClient entityResolutionClient) {
        this.entityResolutionClient = entityResolutionClient;
    }

    /**
     * Converts a DTO to an API request.
     *
     * @param dto The DTO to be converted.
     * @return The converted GetMatchIdRequest.
     */
    private GetMatchIdRequest convertToRequest(GetMatchIdRequestDTO dto) {
        return GetMatchIdRequest.builder()
                .record(dto.getRecord())
                .workflowName(dto.getWorkflowName())
                .build();
    }

    /**
     * Fetches the Match ID for a given request DTO.
     *
     * @param dto The DTO containing request details.
     * @return The response from the client.
     */
    public GetMatchIdResponse getMatchId(GetMatchIdRequestDTO dto) {
        GetMatchIdRequest request = convertToRequest(dto);
        return entityResolutionClient.getMatchId(request);
    }
}

