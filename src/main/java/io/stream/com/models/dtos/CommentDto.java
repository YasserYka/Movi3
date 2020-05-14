package io.stream.com.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private Long movieId;
    private String username;
    private String body;

}
