package io.stream.com.repositories;

import io.stream.com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { 
    public Optional<User> findByUsername(String username); 

    public Optional<User> findByEmail(String email); 

    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);
    
    @Transactional
    @Modifying
    @Query("UPDATE User a set a.enabled = true WHERE a.email = :email")
    public void enableAccountByEmail(String email);


    @Transactional
    @Modifying
    @Query("UPDATE User a set a.lastSeen = :newDate WHERE a.userId = :userId")
    public void updateLastseen(Long userId, Date newDate);

}
