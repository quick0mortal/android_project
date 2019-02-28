package com.example.admisistrator.demo;

import org.json.JSONException;
import org.json.JSONObject;


public class JsonUtil {

    public static JSONObject getJSON(String content){
        try {
            if (content != null) {
                JSONObject jsonObject = new JSONObject(content);
                return jsonObject;
            }
        }catch(JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return null;
    }
}
