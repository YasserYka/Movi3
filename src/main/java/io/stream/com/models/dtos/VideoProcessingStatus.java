package io.stream.com.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoProcessingStatus {

    private int percentage;
    private String filename;
    private int estimatedTime;
    
}
