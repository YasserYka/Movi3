package io.stream.com.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;

    private String title;

    private boolean storedInS3;

    private String originalFilename;

    private String description;

    private int likeCount;

    private float rating;

    private int release;

    private String imageUrl;

    private int viewCount;

    @JsonManagedReference
    @OneToMany(mappedBy="movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Genre> genres;
    
    public void incrementLike(){ likeCount++; }

}
