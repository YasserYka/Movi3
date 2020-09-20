package io.stream.com.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Id;

public class WatchLater {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchLaterId;

    @OneToMany(mappedBy="movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="movieId", referencedColumnName="movieId")
    private Set<Movie> movies;

}
