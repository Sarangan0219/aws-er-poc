package com.example.awserpoc;

import software.amazon.awssdk.services.entityresolution.model.GetMatchIdRequest;
import software.amazon.awssdk.services.entityresolution.model.GetMatchIdResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entity-resolution")
public class EntityResolutionController {

    @Autowired
    private EntityResolutionService service;

    @PostMapping("/get-match-id")
    public GetMatchIdResponse getMatchId(@RequestBody GetMatchIdRequestDTO dto) {
        return service.getMatchId(dto);
    }
}
