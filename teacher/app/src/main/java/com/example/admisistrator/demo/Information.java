package com.example.admisistrator.demo;


import java.util.HashMap;
import java.util.Map;

public class Information {
    private String title;
    private String description;

    Information(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> toMap(){
        HashMap<String, String> outMap = new HashMap<>();
        outMap.put(Constant.USERNAME, this.getTitle());
        outMap.put(Constant.REQUIREMENT_MESSAGE, this.getDescription());

        return outMap;
    }
}
