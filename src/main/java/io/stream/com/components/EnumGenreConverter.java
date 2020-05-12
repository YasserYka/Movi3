package io.stream.com.components;

import org.springframework.stereotype.Component;

import io.stream.com.models.enums.GenreType;

import org.springframework.core.convert.converter.Converter;

@Component
public class EnumGenreConverter implements Converter<String, GenreType>{

    @Override
    public GenreType convert(String source) { return GenreType.of(source); }
    
}