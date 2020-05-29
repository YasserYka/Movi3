package io.stream.com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static String convertToSimpleFormat(Date date) {
        return simpleDateFormat.format(new Date());
    }

    public static Long getCurrentTimeInMilliseconds(){ 
        return new Date().getTime(); 
    }

    private static int getCreationTimeInMilliseconds(Date date){ 
        return (int) (date.getTime() - getCurrentTimeInMilliseconds()); 
    }

    public static Long dateToMilliseconds(Date date) { 
        return date.getTime(); 
    }

    public static int creationDateInHours(Date date){ 
        return (getCreationTimeInMilliseconds(date) / (1000*60*60)) % 24; 
    }    
    
    public static Date dateAfter(int hours){ 
        return new Date(new Date().getTime() + TimeUnit.HOURS.toMillis(hours));
    }
}