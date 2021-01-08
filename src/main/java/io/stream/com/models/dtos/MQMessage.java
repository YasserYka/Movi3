package io.stream.com.models.dtos;

import io.stream.com.models.enums.MQEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MQMessage {

    private String filename;
    private MQEvent event;
    private int estimatedTime;
    
}
