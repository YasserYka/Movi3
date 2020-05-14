package io.stream.com.utils;

import java.util.Date;

public class TimeUtil {

    public static Long getCurrentTimeInMilliseconds(){ return new Date().getTime(); }

    private static int getCreationTimeInMilliseconds(Date date){ return (int) (date.getTime() - getCurrentTimeInMilliseconds()); }

    public static Long dateToMilliseconds(Date date) { return date.getTime(); }

    public static Long millisecondsToHours(Long milliseconds){ return (milliseconds / (1000*60*60)) % 24; }

    public static int creationDateInHours(Date date){ return (getCreationTimeInMilliseconds(date) / (1000*60*60)) % 24; }    
    
}