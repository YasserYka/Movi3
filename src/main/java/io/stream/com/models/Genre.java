package io.stream.com.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.stream.com.models.enums.GenreType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {
    private GenreType genre;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movieId", referencedColumnName="movieId")
    private Movie movie;
}