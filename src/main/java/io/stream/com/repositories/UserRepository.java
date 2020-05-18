package io.stream.com.repositories;

import io.stream.com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { 
    public Optional<User> findByUsername(String username); 

    public boolean existsByEmail(String email);
    
    @Transactional
    @Modifying
    @Query("UPDATE User a set a.enabled = true WHERE a.email = :email")
    public void enableAccountByEmail(String email);
}
