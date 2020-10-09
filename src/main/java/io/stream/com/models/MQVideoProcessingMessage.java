
package io.stream.com.models;

import io.stream.com.models.enums.VideoProcessType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MQVideoProcessingMessage {

    private VideoProcessType event;
    private String url;

}