package io.stream.com.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MQMessage {

    private String event;
    private String url;
    private boolean storedInS3;

    @Override
    public String toString(){
        return "{\"event\" :" + event + "," +
        "\"url\" : " + url + "," + 
        "\"storedInS3\" : " + storedInS3 + "}";
    }
    
}