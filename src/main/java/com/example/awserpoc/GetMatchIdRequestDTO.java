package com.example.awserpoc;

import java.util.Map;

public class GetMatchIdRequestDTO {
    private Map<String, String> record;
    private String workflowName;

    public Map<String, String> getRecord() {
        return record;
    }

    public void setRecord(Map<String, String> record) {
        this.record = record;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }
}

