package io.stream.com.models.dtos;

import java.util.Date;

import io.stream.com.utils.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long movieId;
    private int avatarId;
    private Date date;
    private String username;
    private String body;


    public String getDate(){
        return TimeUtil.convertToSimpleFormat(date);
    }
    
}
