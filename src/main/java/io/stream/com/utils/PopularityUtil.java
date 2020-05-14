package io.stream.com.utils;

import java.util.Date;

public class PopularityUtil {

    public static double calculateScore(int numberOfLikes, Date creationDate){
        return (numberOfLikes - 1) / Math.pow(TimeUtil.creationDateInHours(creationDate) + 2, 1.5);
    }
    
}