package com.example.admisistrator.demo;

import java.util.HashMap;
import java.util.Map;

public class RequirementInfo {
    private String username;
    private String requirementMessage;

    public RequirementInfo(String username, String requirementMessage) {
        this.username = username;
        this.requirementMessage = requirementMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequirementMessage() {
        return requirementMessage;
    }

    public void setRequirementMessage(String requirementMessage) {
        this.requirementMessage = requirementMessage;
    }

    public Map<String, String> toMap(){
        HashMap<String, String> outMap = new HashMap<>();
        outMap.put(Constant.USERNAME, this.getUsername());
        outMap.put(Constant.REQUIREMENT_MESSAGE, this.getRequirementMessage());

        return outMap;
    }
}
