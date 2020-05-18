package io.stream.com.utils;

import java.util.HashMap;

public class MediaUtil {

    private static final HashMap<String, Boolean> supportedMediaFormats = new HashMap<>();

    static { 
        supportedMediaFormats.put("mp4", true); 
    }

    public static boolean isFormatSupported(String filename){ 
        return !supportedMediaFormats.containsKey(getExtension(filename)); 
    }

    private static String getExtension(String filename){ 
        return filename.substring(filename.lastIndexOf(".")); 
    }
}
