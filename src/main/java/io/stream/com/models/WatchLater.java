package io.stream.com.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="watchlater")
@Entity
public class WatchLater {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchLaterId;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId", referencedColumnName="userId")
    private User user;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Movie> movies;

}
