package io.stream.com.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private Long movieId;
    private Instant date;
    private String username;
    private String body;

}
