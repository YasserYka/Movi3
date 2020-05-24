package io.stream.com.models;

import io.stream.com.utils.VideoProcessType;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MQMessage {

    private VideoProcessType event;
    private String url;
    private boolean storedInS3;

    @Override
    public String toString(){
        return "{\"event\" :" + event.toString() + "," +
        "\"url\" : " + url + "," + 
        "\"storedInS3\" : " + storedInS3 + "}";
    }
    
}