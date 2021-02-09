package io.stream.com.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import io.stream.com.models.enums.MQEvent;

public class BashUtil {

    private final static Map<MQEvent, String> BASH_SCRIPT_MAPPER = BashUtil.buildEventToScriptMapper();

    public static Map<MQEvent, String> buildEventToScriptMapper(){
        Map<MQEvent, String> mapper = new HashMap<MQEvent, String>();

        mapper.put(MQEvent.TO_720p, "yes y | ffmpeg -i sample.mp4 -an -c:v libx264 -x264opts 'keyint=24:min-keyint=24:no-scenecut' -vf 'scale=-1:720' -crf 27 720.mp4 2>&1");
        mapper.put(MQEvent.TO_480p, "yes y | ffmpeg -i sample.mp4 -an -c:v libx264 -x264opts 'keyint=24:min-keyint=24:no-scenecut' -vf 'scale=-1:480' -crf 27 480.mp4 2>&1");
        mapper.put(MQEvent.TO_360p, "yes y | ffmpeg -i sample.mp4 -an -c:v libx264 -x264opts 'keyint=24:min-keyint=24:no-scenecut' -vf 'scale=-1:360' -crf 27 360.mp4 2>&1");
        mapper.put(MQEvent.GENERATE_MANIFEST, "yes y | MP4Box -dash 1000 -rap -frag-rap -profile onDemand -out manifest.mpd 480.mp4 360.mp4 240.mp4 audio.mp4 2>&1");
        mapper.put(MQEvent.EXTRACT_AUDIO, "yes y | ffmpeg -i sample.mp4 -c:a copy -vn audio.mp4 2>&1");
        
        return mapper;
    }

    public static ProcessBuilder buildProcessFor(MQEvent event){

        return new ProcessBuilder("/bin/bash", "-c", BASH_SCRIPT_MAPPER.get(event));
    }

    public static BufferedReader getBufferedReader(Process process){

        return new BufferedReader(new InputStreamReader(process.getInputStream()));
    }
}
