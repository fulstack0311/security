package com.duynt.projectsecurity.model.response;

import java.util.Map;

public class ResponseMsg {
    private int status;
    private String timestamp;
    private Map message;

    public ResponseMsg(int status, String timestamp, Map message) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map getMessage() {
        return message;
    }

    public void setMessage(Map message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
