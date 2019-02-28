package data;

import java.util.HashMap;
import java.util.Map;


public class MessageInfo {
    private String from;
    private String content;
    private String to;

    public MessageInfo() {

    }

    public MessageInfo(String from, String content, String to) {
        this.from = from;
        this.content = content;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, String> toMap(){

        HashMap<String, String> outMap = new HashMap<>();
        outMap.put(Constant.FROM, this.getFrom());
        outMap.put(Constant.CONTENT, this.getContent());
        outMap.put(Constant.TO, this.getTo());

        return outMap;
    }
}
