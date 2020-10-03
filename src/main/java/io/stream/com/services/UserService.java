package io.stream.com.services;

import io.stream.com.controllers.exceptions.UserNotFoundException;
import io.stream.com.mappers.UserMapper;
import io.stream.com.models.User;
import io.stream.com.models.dtos.ProfileDto;
import io.stream.com.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthService authService;

    @Autowired
    private CacheService cacheService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);

        if(userOptional.isPresent())
            return userOptional.get();

        log.error("User not found with username {}", username);
        throw new UsernameNotFoundException(username);
    }

    public List<User> getAll(){
     
        return repository.findAll();
    }

    public boolean isEmailExists(String email) { 
     
        return repository.existsByEmail(email); 
    }

    public boolean isUsernameExists(String username) { 
     
        return repository.existsByUsername(username); 
    }

    public ProfileDto update(ProfileDto profileDto, Long id){
		Optional<User> optionalUser = repository.findById(id);

		if(!optionalUser.isPresent())
            throw new UserNotFoundException(id);

		User user = optionalUser.get();
		user.setUsername(profileDto.getUsername());
        user.setAvatarId(profileDto.getAvatarId());
        user.setBio(profileDto.getBio());
        user.setFullName(profileDto.getFullName());

		return UserMapper.mapProfile(repository.save(user));
	}

    public void enableAccount(String token){

        repository.enableAccountByEmail(cacheService.getEmailOfToken(token));
    }

    public void updateLastseen(){

        repository.updateLastSeen(authService.getCurrentLoggedInUser().getUserId(), new Date());
    }

    public ProfileDto getProfile() { 

        return UserMapper.mapProfile(authService.getCurrentLoggedInUser());
    }

    public User save(User user){

        return repository.save(user);
    }
}
