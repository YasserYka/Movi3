package io.stream.com.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenreType {
    horror("horror"),
    action("action"),
    comedy("comedy"),
    adventure("adventure"),
    crime("crime"),
    romance("romance"),
    documetary("documetary"),
    anime("anime");

    private String value;

    @JsonValue
    public String getValue() { return value; }

    @JsonCreator
    public static GenreType of(String value){
        if(value == null)
            return null;

        for(GenreType genre : GenreType.values())
            if(genre.getValue().equals(value))
                return genre;

        return null;
    }

    GenreType(String value){ this.value = value; }

}