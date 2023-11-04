package com.example.awserpoc;

import com.example.awserpoc.dto.GetMatchIdRequestDTO;
import software.amazon.awssdk.services.entityresolution.model.GetMatchIdResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles AWS Entity Resolution related API calls.
 */
@RestController
@RequestMapping("/api/entity-resolution")
public class EntityResolutionController {

    private final EntityResolutionService entityResolutionService;

    @Autowired
    public EntityResolutionController(EntityResolutionService entityResolutionService) {
        this.entityResolutionService= entityResolutionService;
    }

    /**
     * Endpoint to get match ID for a given request.
     *
     * @param dto The request payload containing necessary details to get the match ID.
     * @return The response from AWS Entity Resolution service containing the match ID.
     */
    @PostMapping("/get-match-id")
    @ResponseStatus(HttpStatus.OK)
    public GetMatchIdResponse getMatchId(@RequestBody GetMatchIdRequestDTO dto) {
        return entityResolutionService.getMatchId(dto);
    }

    // Add More Rest API Calls here

}

