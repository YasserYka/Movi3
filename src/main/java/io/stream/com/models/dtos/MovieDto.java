package io.stream.com.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieDto {
    
    private String title;
    private String description;
    private int release;
}